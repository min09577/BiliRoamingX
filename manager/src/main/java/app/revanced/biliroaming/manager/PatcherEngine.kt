package app.revanced.biliroaming.manager

import android.content.Context
import android.net.Uri
import android.util.Log
import app.revanced.patcher.PatchBundleLoader
import app.revanced.patcher.Patcher
import app.revanced.patcher.PatcherConfig
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

object PatcherEngine {

    private const val TAG = "BiliRoamingX-Mgr"

    fun patch(context: Context, apkUri: Uri): File {
        val cacheDir = File(context.cacheDir, "patcher")
        cacheDir.mkdirs()

        return try {
            // 1. 复制 APK
            Log.i(TAG, "步骤1/6: 复制 APK...")
            val inputApk = File(cacheDir, "input.apk")
            context.contentResolver.openInputStream(apkUri)?.use { src ->
                FileOutputStream(inputApk).use { dst -> src.copyTo(dst) }
            } ?: throw IllegalStateException("无法读取 APK")

            // 2. 解压补丁资源
            Log.i(TAG, "步骤2/6: 解压资源...")
            val patchesJar = File(cacheDir, "patches.jar")
            context.assets.open("patches.jar").use { src ->
                FileOutputStream(patchesJar).use { dst -> src.copyTo(dst) }
            }
            val integrationsApk = File(cacheDir, "integrations.apk")
            context.assets.open("integrations.apk").use { src ->
                FileOutputStream(integrationsApk).use { dst -> src.copyTo(dst) }
            }

            // 3. 加载补丁
            Log.i(TAG, "步骤3/6: 加载补丁...")
            val patchBundle = PatchBundleLoader.Dex(patchesJar)
            Log.i(TAG, "补丁加载成功，共 ${patchBundle.size} 个")

            // 4. 初始化 Patcher (revanced-patcher JAR 已修补，跳过 aapt NPE)
            Log.i(TAG, "步骤4/6: 初始化 Patcher...")
            val tmpDir = File(cacheDir, "tmp").also { it.mkdirs() }
            val config = PatcherConfig(inputApk, tmpDir, null, null, false, false)
            val patcher = try {
                Patcher(config)
            } catch (e: Exception) {
                throw IllegalStateException("Patcher 初始化失败: ${e.stackTraceString()}")
            }

            // 5. 执行注入
            patcher.acceptPatches(patchBundle)
            patcher.acceptIntegrations(listOf(integrationsApk))
            Log.i(TAG, "步骤5/6: 执行注入...")
            try {
                runBlocking { patcher.apply(false).first() }
                Log.i(TAG, "注入完成")
            } catch (e: Exception) {
                throw IllegalStateException("补丁注入失败: ${e.stackTraceString()}")
            }

            // 6. 保存 DEX + 重打包 + 签名
            val result = try {
                patcher.get()
            } catch (e: Exception) {
                throw IllegalStateException("获取结果失败: ${e.stackTraceString()}")
            }
            val dexDir = File(cacheDir, "dex").also { it.mkdirs() }
            result.dexFiles.forEach { dex ->
                FileOutputStream(File(dexDir, dex.name)).use { out ->
                    dex.stream.use { it.copyTo(out) }
                }
            }

            Log.i(TAG, "步骤6/6: 重打包...")
            val repackaged = File(cacheDir, "biliroamingx-repack.apk")
            repackageApk(inputApk, dexDir, repackaged)

            Log.i(TAG, "签名中...")
            val signed = File(cacheDir, "biliroamingx-signed.apk")
            try {
                ApkSigner.sign(repackaged, signed)
            } catch (e: Exception) {
                throw IllegalStateException("APK 签名失败: ${e.stackTraceString()}")
            }
            signed
        } catch (e: Exception) {
            Log.e(TAG, "注入流程失败", e)
            throw e
        }
    }

    private fun Throwable.stackTraceString(): String {
        val sw = StringWriter()
        printStackTrace(PrintWriter(sw))
        return sw.toString().take(500)
    }

    private fun repackageApk(originalApk: File, dexDir: File, output: File) {
        ZipOutputStream(FileOutputStream(output)).use { zos ->
            ZipFile(originalApk).use { zip ->
                val entries = zip.entries()
                while (entries.hasMoreElements()) {
                    val entry = entries.nextElement()
                    if (entry.name.endsWith(".dex")) continue
                    zos.putNextEntry(ZipEntry(entry.name))
                    if (!entry.isDirectory) {
                        zip.getInputStream(entry).use { it.copyTo(zos) }
                    }
                    zos.closeEntry()
                }
            }
            dexDir.listFiles()?.sortedBy { it.name }?.forEach { dex ->
                zos.putNextEntry(ZipEntry(dex.name))
                FileInputStream(dex).use { it.copyTo(zos) }
                zos.closeEntry()
            }
        }
    }
}
