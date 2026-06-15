package app.revanced.biliroaming.manager

import android.content.Context
import android.net.Uri
import android.util.Log
import app.revanced.patcher.PatchBundleLoader
import app.revanced.patcher.Patcher
import app.revanced.patcher.PatcherOptions
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.PrintWriter
import java.io.StringWriter
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

/**
 * BiliRoamingX 补丁引擎
 * 使用 revanced-patcher（kofua 19.3.1.2）将补丁注入到 某站 APK
 * 流程: 选 APK → DEX 注入 → 重打包 → 签名 → 安装
 */
object PatcherEngine {

    private const val TAG = "BiliRoamingX-Mgr"

    fun patch(context: Context, apkUri: Uri): File {
        val cacheDir = File(context.cacheDir, "patcher")
        cacheDir.mkdirs()

        return try {
            // 1. 复制用户选择的 APK 到本地
            Log.i(TAG, "步骤1/6: 复制 APK 到本地...")
            val inputApk = File(cacheDir, "input.apk")
            context.contentResolver.openInputStream(apkUri)?.use { input ->
                FileOutputStream(inputApk).use { output ->
                    val copied = input.copyTo(output)
                    Log.i(TAG, "APK 复制完成: ${copied} bytes")
                }
            } ?: throw IllegalStateException("无法读取 APK 文件，请确认文件存在且可访问")

            // 2. 从 assets 解出 patches.jar 和 integrations.apk
            Log.i(TAG, "步骤2/6: 解压补丁资源...")
            val assetList = context.assets.list("")?.toList() ?: emptyList()
            Log.i(TAG, "Assets 内容: $assetList")

            val patchesJar = try {
                File(cacheDir, "patches.jar").also { f ->
                    context.assets.open("patches.jar").use { src ->
                        FileOutputStream(f).use { out ->
                            val size = src.copyTo(out)
                            Log.i(TAG, "patches.jar 解压完成: $size bytes")
                        }
                    }
                }
            } catch (e: Exception) {
                val available = context.assets.list("")?.joinToString() ?: "无"
                throw IllegalStateException("缺少 patches.jar！assets 中只有: $available")
            }

            val integrationsApk = try {
                File(cacheDir, "integrations.apk").also { f ->
                    context.assets.open("integrations.apk").use { src ->
                        FileOutputStream(f).use { out ->
                            val size = src.copyTo(out)
                            Log.i(TAG, "integrations.apk 解压完成: $size bytes")
                        }
                    }
                }
            } catch (e: Exception) {
                val available = context.assets.list("")?.joinToString() ?: "无"
                throw IllegalStateException("缺少 integrations.apk！assets 中只有: $available")
            }

            // 3. 加载补丁 — PatchBundleLoader.Dex (Android 原生 DEX 加载)
            Log.i(TAG, "步骤3/6: 加载补丁...")
            val patchBundle = try {
                @Suppress("UNCHECKED_CAST")
                val loader = PatchBundleLoader.Dex(patchesJar)
                loader.also { Log.i(TAG, "补丁加载成功，共 ${it.size} 个补丁") }
            } catch (e: Exception) {
                throw IllegalStateException("补丁加载失败: ${stackTraceString(e)}")
            }

            Log.i(TAG, "步骤4/6: 初始化 Patcher...")
            val options = PatcherOptions(
                inputFile = inputApk,
                resourceCachePath = File(cacheDir, "resources"),
                aaptBinaryPath = null,
                frameworkFileDirectory = null,
                multithreadingDexFileWriter = false
            )
            val patcher = try {
                Patcher(options)
            } catch (e: Exception) {
                throw IllegalStateException("Patcher 初始化失败: ${stackTraceString(e)}")
            }

            patcher.acceptPatches(patchBundle)
            patcher.acceptIntegrations(listOf(integrationsApk))

            Log.i(TAG, "步骤5/6: 执行注入...")
            try {
                runBlocking { patcher.apply(false).first() }
                Log.i(TAG, "注入完成")
            } catch (e: Exception) {
                throw IllegalStateException("补丁注入执行失败: ${stackTraceString(e)}")
            }

            // 4. 保存补丁后的 DEX 文件
            val result = try {
                patcher.get()
            } catch (e: Exception) {
                throw IllegalStateException("获取注入结果失败: ${stackTraceString(e)}")
            }

            val dexDir = File(cacheDir, "dex")
            dexDir.mkdirs()
            Log.i(TAG, "保存 ${result.dexFiles.size} 个 DEX 文件...")
            result.dexFiles.forEach { dex ->
                FileOutputStream(File(dexDir, dex.name)).use { out ->
                    dex.stream.use { it.copyTo(out) }
                }
            }

            // 5. 重打包 APK：原 APK 替换 DEX
            Log.i(TAG, "步骤6/6: 重打包 APK...")
            val repackaged = File(cacheDir, "biliroamingx-repack.apk")
            try {
                repackageApk(inputApk, dexDir, repackaged)
            } catch (e: Exception) {
                throw IllegalStateException("APK 重打包失败: ${stackTraceString(e)}")
            }

            // 6. 签名
            Log.i(TAG, "签名 APK...")
            val signed = File(cacheDir, "biliroamingx-signed.apk")
            try {
                ApkSigner.sign(repackaged, signed)
                Log.i(TAG, "签名完成: ${signed.length()} bytes")
            } catch (e: Exception) {
                throw IllegalStateException("APK 签名失败: ${stackTraceString(e)}")
            }

            signed
        } catch (e: Exception) {
            Log.e(TAG, "注入流程失败", e)
            throw e
        }
    }

    private fun stackTraceString(e: Throwable): String {
        val sw = StringWriter()
        e.printStackTrace(PrintWriter(sw))
        return sw.toString().take(500)
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
