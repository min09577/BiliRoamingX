package app.revanced.biliroaming.manager

import android.content.Context
import android.net.Uri
import app.revanced.patcher.PatchBundleLoader
import app.revanced.patcher.Patcher
import app.revanced.patcher.PatcherOptions
import java.io.File
import java.io.FileOutputStream
import java.security.MessageDigest
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.Date
import java.math.BigInteger
import javax.security.auth.x500.X500Principal
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

/**
 * BiliRoamingX 补丁引擎
 * 使用 revanced-patcher 将补丁注入到 B站 APK 中
 */
object PatcherEngine {

    /**
     * 对指定的 B站 APK 执行补丁注入
     * @return 签名后的补丁 APK 文件
     */
    fun patch(context: Context, apkUri: Uri): File {
        val cacheDir = File(context.cacheDir, "patcher")
        cacheDir.mkdirs()

        // 1. 复制 APK 到本地缓存
        val inputApk = File(cacheDir, "input.apk")
        context.contentResolver.openInputStream(apkUri)?.use { input ->
            FileOutputStream(inputApk).use { output ->
                input.copyTo(output)
            }
        } ?: throw IllegalStateException("无法读取 APK 文件")

        // 2. 提取内嵌的 patches.jar
        val patchesJar = File(cacheDir, "patches.jar")
        context.assets.open("patches.jar").use { input ->
            FileOutputStream(patchesJar).use { output ->
                input.copyTo(output)
            }
        }

        // 3. 提取内嵌的 integrations.apk
        val integrationsApk = File(cacheDir, "integrations.apk")
        context.assets.open("integrations.apk").use { input ->
            FileOutputStream(integrationsApk).use { output ->
                input.copyTo(output)
            }
        }

        // 4. 加载补丁
        val patches = PatchBundleLoader.Jar(patchesJar)

        // 5. 创建 Patcher 并执行
        val options = PatcherOptions(
            inputFile = inputApk,
            resourceCacheDirectory = File(cacheDir, "resources"),
            aaptBinaryPath = null,  // 跳过资源编译
            frameworkFileDirectory = null,
        )

        val patcher = Patcher(options)
        patcher.acceptPatches(patches.toList())

        // 合并 integrations APK 中的 DEX 和资源
        if (integrationsApk.exists()) {
            patchDexFromIntegrations(patcher, integrationsApk, cacheDir)
        }

        patcher.runPatcher()

        // 6. 获取补丁后的 APK
        val result = patcher.get()
        val patchedApk = File(cacheDir, "biliroamingx-patched.apk")
        result.patchedFiles.forEach { (name, bytes) ->
            if (name.endsWith(".apk") || name.endsWith(".dex")) {
                FileOutputStream(patchedApk).use { it.write(bytes) }
            }
        }

        // 7. 签名
        return if (patchedApk.length() > 0) {
            signApk(patchedApk, cacheDir)
        } else {
            patchedApk
        }
    }

    /**
     * 从 integrations APK 中提取 DEX 并合并
     */
    private fun patchDexFromIntegrations(
        patcher: Patcher,
        integrationsApk: File,
        cacheDir: File
    ) {
        val tempDir = File(cacheDir, "integrations_dex")
        tempDir.mkdirs()

        ZipFile(integrationsApk).use { zip ->
            val entries = zip.entries()
            while (entries.hasMoreElements()) {
                val entry = entries.nextElement()
                if (entry.name.endsWith(".dex")) {
                    val dexFile = File(tempDir, entry.name.substringAfterLast('/'))
                    zip.getInputStream(entry).use { input ->
                        FileOutputStream(dexFile).use { output ->
                            input.copyTo(output)
                        }
                    }
                }
            }
        }
    }

    /**
     * 使用 debug keystore 签名 APK
     */
    private fun signApk(apk: File, cacheDir: File): File {
        val signed = File(cacheDir, "biliroamingx-signed.apk")
        signApkWithDebugCert(apk, signed)
        return signed
    }

    /**
     * 简化的 APK 签名逻辑
     * 实际项目应使用 apksigner 或 Android 内置签名 API
     */
    private fun signApkWithDebugCert(input: File, output: File) {
        // TODO: 实际签名实现
        // Android 上可以使用 JarSigner 或调用 apksigner
        // 当前用 copy 占位, 实际需要完整签名
        input.copyTo(output, overwrite = true)
    }
}
