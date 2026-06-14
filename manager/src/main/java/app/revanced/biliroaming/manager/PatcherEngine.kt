package app.revanced.biliroaming.manager

import android.content.Context
import android.net.Uri
import app.revanced.patcher.PatchBundleLoader
import app.revanced.patcher.Patcher
import app.revanced.patcher.PatcherOptions
import kotlinx.coroutines.flow.first
import java.io.File
import java.io.FileOutputStream
import kotlinx.coroutines.runBlocking

/**
 * BiliRoamingX 补丁引擎
 * 使用 revanced-patcher (kofua 19.3.1.2) 将补丁注入到 某站 APK
 */
object PatcherEngine {

    fun patch(context: Context, apkUri: Uri): File {
        val cacheDir = File(context.cacheDir, "patcher")
        cacheDir.mkdirs()

        // 1. 复制用户选择的 APK 到本地
        val inputApk = File(cacheDir, "input.apk")
        context.contentResolver.openInputStream(apkUri)?.use { input ->
            FileOutputStream(inputApk).use { output ->
                input.copyTo(output)
            }
        } ?: throw IllegalStateException("无法读取 APK")

        // 2. 从 assets 解出 patches.jar 和 integrations.apk
        val patchesJar = File(cacheDir, "patches.jar")
        context.assets.open("patches.jar").use { input ->
            FileOutputStream(patchesJar).use { output ->
                input.copyTo(output)
            }
        }
        val integrationsApk = File(cacheDir, "integrations.apk")
        context.assets.open("integrations.apk").use { input ->
            FileOutputStream(integrationsApk).use { output ->
                input.copyTo(output)
            }
        }

        // 3. 加载补丁 -> 创建 Patcher -> 注入
        val patches = PatchBundleLoader.Jar(patchesJar)
        val options = PatcherOptions(
            inputFile = inputApk,
            resourceCachePath = File(cacheDir, "resources"),
            aaptBinaryPath = null,      // 手机上没 aapt, 跳过资源编译
            frameworkFileDirectory = null,
            multithreadingDexFileWriter = false
        )
        val patcher = Patcher(options)
        patcher.acceptPatches(patches.toList())
        patcher.acceptIntegrations(listOf(integrationsApk))

        // apply(false) = 同步在当前线程执行, 返回 Flow<PatchResult>
        runBlocking {
            patcher.apply(false).first()
        }

        // 4. 拿到补丁结果, 写出 DEX 文件
        val result = patcher.get()
        result.dexFiles.forEach { dex ->
            val outFile = File(cacheDir, dex.name)
            dex.stream.use { input ->
                FileOutputStream(outFile).use { output ->
                    input.copyTo(output)
                }
            }
        }

        // 5. 重新打包 APK + 签名
        // TODO: 实现 APK 重打包 (替换原 APK 中的 DEX) + V1/V2 签名
        val patchedApk = File(cacheDir, "biliroamingx-patched.apk")
        inputApk.copyTo(patchedApk, overwrite = true)
        return patchedApk
    }
}
