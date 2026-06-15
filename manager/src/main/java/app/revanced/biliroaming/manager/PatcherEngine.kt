package app.revanced.biliroaming.manager

import android.content.Context
import android.net.Uri
import app.revanced.patcher.PatchBundleLoader
import app.revanced.patcher.Patcher
import app.revanced.patcher.PatcherOptions
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

/**
 * BiliRoamingX 补丁引擎
 * 使用 revanced-patcher（kofua 19.3.1.2）将补丁注入到 某站 APK
 * 流程: 选 APK → DEX 注入 → 重打包 → 签名 → 安装
 */
object PatcherEngine {

    fun patch(context: Context, apkUri: Uri): File {
        val cacheDir = File(context.cacheDir, "patcher")
        cacheDir.mkdirs()

        // 1. 复制用户选择的 APK 到本地
        val inputApk = File(cacheDir, "input.apk")
        context.contentResolver.openInputStream(apkUri)?.use { input ->
            FileOutputStream(inputApk).use { input.copyTo(it) }
        } ?: throw IllegalStateException("无法读取 APK")

        // 2. 从 assets 解出 patches.jar 和 integrations.apk
        val patchesJar = File(cacheDir, "patches.jar")
        context.assets.open("patches.jar").use {
            FileOutputStream(patchesJar).use { out -> it.copyTo(out) }
        }
        val integrationsApk = File(cacheDir, "integrations.apk")
        context.assets.open("integrations.apk").use {
            FileOutputStream(integrationsApk).use { out -> it.copyTo(out) }
        }

        // 3. 加载补丁 → 创建 Patcher → 注入
        val patches = PatchBundleLoader.Jar(patchesJar)
        val options = PatcherOptions(
            inputFile = inputApk,
            resourceCachePath = File(cacheDir, "resources"),
            aaptBinaryPath = null,
            frameworkFileDirectory = null,
            multithreadingDexFileWriter = false
        )
        val patcher = Patcher(options)
        patcher.acceptPatches(patches.toList())
        patcher.acceptIntegrations(listOf(integrationsApk))

        runBlocking { patcher.apply(false).first() }

        // 4. 保存补丁后的 DEX 文件
        val result = patcher.get()
        val dexDir = File(cacheDir, "dex")
        dexDir.mkdirs()
        result.dexFiles.forEach { dex ->
            FileOutputStream(File(dexDir, dex.name)).use { out ->
                dex.stream.use { it.copyTo(out) }
            }
        }

        // 5. 重打包 APK：原 APK 替换 DEX
        val repackaged = File(cacheDir, "biliroamingx-repack.apk")
        repackageApk(inputApk, dexDir, repackaged)

        // 6. 签名 (TODO: 接入系统 apksigner 或自实现)
        // 现在返回未签名的 APK, 需要用户手动签名后安装
        // 或使用 Android PackageInstaller 安装未签名 APK
        return repackaged
    }

    /**
     * APK 重打包：读取原始 APK，替换其中的 .dex 文件
     */
    private fun repackageApk(originalApk: File, dexDir: File, output: File) {
        ZipOutputStream(FileOutputStream(output)).use { zos ->
            ZipFile(originalApk).use { zip ->
                val entries = zip.entries()
                while (entries.hasMoreElements()) {
                    val entry = entries.nextElement()
                    // 跳过原始 DEX 文件，后面用新的替换
                    if (entry.name.endsWith(".dex")) continue
                    // 保持其他所有文件不变
                    zos.putNextEntry(ZipEntry(entry.name))
                    if (!entry.isDirectory) {
                        zip.getInputStream(entry).use { it.copyTo(zos) }
                    }
                    zos.closeEntry()
                }
            }
            // 写入新的 DEX 文件
            dexDir.listFiles()?.sortedBy { it.name }?.forEach { dex ->
                zos.putNextEntry(ZipEntry(dex.name))
                FileInputStream(dex).use { it.copyTo(zos) }
                zos.closeEntry()
            }
        }
    }
}
