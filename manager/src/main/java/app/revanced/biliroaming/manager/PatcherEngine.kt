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
     * 创建 Patcher，优先尝试跳过资源解码，失败则用假 aapt
     */
    private fun createPatcher(inputApk: File, tmpDir: File, ctx: Context): Patcher {
        // 方案1: 设置 ResourceMode.NONE 跳过 decodeResources
        try {
            val config = PatcherConfig(inputApk, tmpDir, null, null, false, false)
            val modeClass = Class.forName("app.revanced.patcher.data.ResourceContext\$ResourceMode")
            val noneVal = modeClass.getDeclaredField("NONE").get(null)

            // 直接修改 private 字段
            val field = PatcherConfig::class.java.getDeclaredField("resourceMode")
            field.isAccessible = true
            field.set(config, noneVal)

            val actual = field.get(config)
            Log.i(TAG, "ResourceMode = $actual")
            return Patcher(config)
        } catch (e: Exception) {
            Log.w(TAG, "ResourceMode.NONE 方案失败: ${e.message}，尝试假 aapt")
        }

        // 方案2: 用假 aapt 脚本提供 manifest 信息
        val fakeAapt = createFakeAapt(ctx, inputApk, tmpDir.parentFile)
        val config = PatcherConfig(inputApk, tmpDir, fakeAapt, null, false, false)
        return Patcher(config)
    }

    /**
     * 用 PackageManager 解析 APK manifest，生成假 aapt 脚本
     */
    private fun createFakeAapt(ctx: Context, apk: File, cacheDir: File): String {
        // 提取包信息
        var pkg = "unknown"; var vc = 1; var vn = "1.0"; var minSdk = 21; var tgtSdk = 33
        try {
            val info = ctx.packageManager.getPackageArchiveInfo(apk.absolutePath, 0)
            if (info != null) {
                pkg = info.packageName ?: pkg
                vc = info.versionCode
                vn = info.versionName ?: vn
                if (android.os.Build.VERSION.SDK_INT >= 24) {
                    minSdk = info.applicationInfo?.minSdkVersion ?: minSdk
                    tgtSdk = info.applicationInfo?.targetSdkVersion ?: tgtSdk
                }
                Log.i(TAG, "APK: $pkg v$vn($vc) SDK$minSdk-$tgtSdk")
            }
        } catch (e: Exception) {
            Log.w(TAG, "PackageParser 失败: ${e.message}")
        }

        val vcHex = "%08x".format(vc)
        val script = """#!/system/bin/sh
case "${'$'}1" in
  dump) echo "package: name='$pkg' versionCode='$vc' versionName='$vn'"
        echo "sdkVersion:'$minSdk'"
        echo "targetSdkVersion:'$tgtSdk'" ;;
  d) echo "N: android=http://schemas.android.com/apk/res/android"
     echo "  E: manifest (line=2)"
     echo "    A: package=\"$pkg\" (Raw: \"$pkg\")"
     echo "    A: android:versionCode=(type 0x10)0x$vcHex"
     echo "    A: android:versionName=\"$vn\" (Raw: \"$vn\")" ;;
esac
exit 0
""".trimIndent()

        val f = File("/data/local/tmp", "biliroamingx_aapt.sh")
        try {
            f.writeText(script)
            f.setExecutable(true, false)
            return f.absolutePath
        } catch (e: Exception) {
            val fb = File(cacheDir, "aapt.sh")
            fb.writeText(script)
            fb.setExecutable(true, false)
            return fb.absolutePath
        }
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
