package app.revanced.biliroaming.manager

import android.content.Context
import android.net.Uri
import android.util.Log
import app.revanced.patcher.Patcher
import app.revanced.patcher.PatcherOptions
import app.revanced.patcher.patch.Patch
import dalvik.system.DexClassLoader
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

            // 3. 加载补丁 — 使用 Android DexClassLoader 代替 URLClassLoader
            Log.i(TAG, "步骤3/6: 加载补丁...")
            val patchClasses = try {
                loadPatchesFromJar(context, patchesJar).also {
                    Log.i(TAG, "补丁加载成功，共 ${it.size} 个补丁")
                }
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

            patcher.acceptPatches(patchClasses)
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

    /**
     * Android 兼容的补丁加载器
     * 使用 DexClassLoader 加载 patches.jar 中的 classes.dex，
     * 扫描 .class 条目获取补丁类名，过滤出 Patch<Context> 的子类
     */
    @Suppress("UNCHECKED_CAST")
    private fun loadPatchesFromJar(context: Context, jar: File): List<Class<out Patch<Context>>> {
        val optimizedDir = File(context.cacheDir, "dexopt").also { it.mkdirs() }

        // 使用 DexClassLoader 加载 JAR 中的 DEX
        val classLoader = DexClassLoader(
            jar.absolutePath,
            optimizedDir.absolutePath,
            null,
            PatcherEngine::class.java.classLoader
        )

        // 从 JAR 中扫描 .class 条目获取补丁类名
        val classNames = mutableListOf<String>()
        ZipFile(jar).use { zip ->
            val entries = zip.entries()
            while (entries.hasMoreElements()) {
                val entry = entries.nextElement()
                if (!entry.isDirectory && entry.name.endsWith(".class")) {
                    // app/revanced/patches/.../FooPatch.class → app.revanced.patches.xxx.FooPatch
                    val className = entry.name
                        .removeSuffix(".class")
                        .replace('/', '.')
                    classNames.add(className)
                }
            }
        }

        Log.i(TAG, "JAR 中共发现 ${classNames.size} 个类，正在加载...")

        val patchClasses = mutableListOf<Class<out Patch<Context>>>()
        for (className in classNames) {
            try {
                val clazz = classLoader.loadClass(className)
                // 检查是否是 Patch 的子类（排除抽象类和内部类）
                if (Patch::class.java.isAssignableFrom(clazz) &&
                    !clazz.isInterface &&
                    !java.lang.reflect.Modifier.isAbstract(clazz.modifiers) &&
                    !className.contains('$')
                ) {
                    patchClasses.add(clazz as Class<out Patch<Context>>)
                    Log.d(TAG, "  加载补丁: $className")
                }
            } catch (e: ClassNotFoundException) {
                Log.w(TAG, "跳过无法加载的类: $className - ${e.message}")
            } catch (e: NoClassDefFoundError) {
                Log.w(TAG, "跳过缺少依赖的类: $className - ${e.message}")
            }
        }

        Log.i(TAG, "成功加载 ${patchClasses.size} 个补丁类")
        return patchClasses
    }
}
