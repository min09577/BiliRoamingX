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

            // 4. 初始化 Patcher
            Log.i(TAG, "步骤4/6: 初始化 Patcher...")
            val tmpDir = File(cacheDir, "tmp").also { it.mkdirs() }
            val patcher = createPatcher(inputApk, tmpDir, context)
            
            // 5. 执行注入
            patcher.acceptPatches(patchBundle)
            patcher.acceptIntegrations(listOf(integrationsApk))
            Log.i(TAG, "步骤5/6: 执行注入...")
            runBlocking { patcher.apply(false).first() }
            Log.i(TAG, "注入完成")
            
            // 6. 保存 DEX + 重打包 + 签名
            val result = patcher.get()
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
            ApkSigner.sign(repackaged, signed)
            signed
        } catch (e: Exception) {
            Log.e(TAG, "注入流程失败", e)
            throw e
        }
    }

    /**
     * 创建 Patcher，通过反射彻底禁用资源解码
     */
    private fun createPatcher(inputApk: File, tmpDir: File, ctx: Context): Patcher {
        val config = PatcherConfig(inputApk, tmpDir, null, null, false, false)
        
        // 多方案并行尝试禁用资源解码
        var success = false
        
        // 方案1: 设置 resourceMode = NONE
        try {
            val modeClass = Class.forName("app.revanced.patcher.data.ResourceContext\$ResourceMode")
            val noneVal = modeClass.getDeclaredField("NONE").get(null)
            val field = PatcherConfig::class.java.getDeclaredField("resourceMode")
            field.isAccessible = true
            field.set(config, noneVal)
            Log.i(TAG, "ResourceMode 设置为 NONE")
            success = true
        } catch (e: Exception) {
            Log.w(TAG, "设置 ResourceMode 失败: ${e.message}")
        }
        
        // 方案2: 修改 resourceConfig 中的 options，禁用 renameManifestPackage
        // 这是 getRenameManifestPackage NPE 的真正源头
        try {
            val resourceConfigField = PatcherConfig::class.java.getDeclaredField("resourceConfig")
            resourceConfigField.isAccessible = true
            val resourceConfig = resourceConfigField.get(config)
            
            // 获取并修改 brut.androlib.Config 中的 renameManifestPackage
            val renameField = resourceConfig.javaClass.getDeclaredField("renameManifestPackage")
            renameField.isAccessible = true
            renameField.set(resourceConfig, "")  // 设为空字符串而非 null
            
            // 同时设置 analysisMode = true 跳过某些资源处理
            val analysisField = resourceConfig.javaClass.getDeclaredField("analysisMode")
            analysisField.isAccessible = true
            analysisField.set(resourceConfig, true)
            
            Log.i(TAG, "resourceConfig 修改成功")
            success = true
        } catch (e: Exception) {
            Log.w(TAG, "修改 resourceConfig 失败: ${e.message}")
        }
        
        // 方案3: 如果实在不行，使用真正的 aapt2 二进制
        if (!success) {
            Log.w(TAG, "反射方案均失败，尝试使用系统 aapt2")
            // 尝试从系统 PATH 找 aapt2
            val systemAapt = findSystemAapt()
            if (systemAapt != null) {
                Log.i(TAG, "使用系统 aapt2: $systemAapt")
                val configWithAapt = PatcherConfig(inputApk, tmpDir, systemAapt, null, false, false)
                return Patcher(configWithAapt)
            }
        }
        
        return Patcher(config)
    }
    
    /**
     * 在系统 PATH 中查找 aapt/aapt2
     */
    private fun findSystemAapt(): String? {
        val paths = arrayOf(
            "/system/bin/aapt",
            "/system/bin/aapt2",
            "/system/xbin/aapt",
            "/system/xbin/aapt2",
            "/data/local/tmp/aapt2"
        )
        for (path in paths) {
            if (File(path).exists() && File(path).canExecute()) {
                return path
            }
        }
        // 尝试 which 命令
        try {
            val process = Runtime.getRuntime().exec(arrayOf("which", "aapt2"))
            val result = process.inputStream.bufferedReader().readLine()
            if (result != null && result.isNotEmpty() && File(result).exists()) {
                return result
            }
        } catch (e: Exception) {
            // ignore
        }
        return null
    }

    private fun stackTraceString(e: Throwable): String {
        val sw = StringWriter()
        e.printStackTrace(PrintWriter(sw))
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
