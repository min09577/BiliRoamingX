package app.revanced.biliroaming.manager

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import app.revanced.biliroaming.manager.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectedPath: String? = null
    private var patchedApkFile: File? = null

    companion object {
        // 某站可能的包名
        private val BILI_PACKAGES = setOf(
            "tv.danmaku.bili",        // 标准版
            "tv.danmaku.bilibilihd",  // HD版
            "com.bilibili.app.in"     // 国际版
        )
    }

    // SAF 文件选择器 (兜底)
    private val apkPicker = registerForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let { onApkUriReady(it) }
    }

    // 安装权限
    private val installPermission = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (canInstallPackages()) installPatchedApk()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        scanInstalledBili()

        binding.btnSelect.setOnClickListener { showApkSourceOptions() }
        binding.btnPatch.setOnClickListener { selectedPath?.let { startPatching(it) } }
        binding.btnInstall.setOnClickListener { checkAndInstall() }
    }

    // -------- 三级降级 --------

    /**
     * 第一级: 扫描已安装的某站版本, 尝试直接读 APK 路径
     */
    private fun scanInstalledBili() {
        val found = mutableListOf<Triple<String, String, String>>() // 包名, 版本, 路径

        for (pkg in BILI_PACKAGES) {
            try {
                val pi = packageManager.getPackageInfo(pkg, 0)
                val name = when (pkg) {
                    "tv.danmaku.bili" -> "标准版"
                    "tv.danmaku.bilibilihd" -> "HD版"
                    "com.bilibili.app.in" -> "国际版"
                    else -> pkg
                }
                val path = pi.applicationInfo.sourceDir
                found.add(Triple("$name v${pi.versionName}", pkg, path))
            } catch (_: PackageManager.NameNotFoundException) {
            }
        }

        if (found.isNotEmpty()) {
            val autoPick = found.first { it.third.endsWith(".apk") || it.third.startsWith("/data/app/") }
            if (autoPick != null) {
                val apkFile = File(autoPick.third)
                if (apkFile.exists() && apkFile.canRead()) {
                    // Android 13 及以下, 直接能读
                    selectedPath = autoPick.third
                    binding.btnPatch.isEnabled = true
                    binding.tvStatus.text = getString(R.string.apk_selected, autoPick.first)
                    return
                }
            }

            // 读不了 (Android 14+), 显示已安装列表并提示手动选
            binding.tvStatus.text = getString(R.string.status_installed_but_locked)
            binding.btnSelect.text = getString(R.string.btn_choose_source)
        } else {
            binding.tvStatus.text = getString(R.string.status_not_installed)
            binding.btnSelect.text = getString(R.string.btn_select)
        }
    }

    /**
     * 第二级/第三级: 弹出选择方式
     */
    private fun showApkSourceOptions() {
        val options = mutableListOf<String>()
        val actions = mutableListOf<() -> Unit>()

        // 尝试注册接收分享的 APK (第二级)
        options.add(getString(R.string.option_share_from_manager))
        actions.add { pickApkFile() }

        // 兜底手动选择 (第三级)
        options.add(getString(R.string.option_manual_pick))
        actions.add {
            apkPicker.launch(arrayOf("application/vnd.android.package-archive"))
        }

        // 如果找到了已安装但读不了, 提示照做
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_select_source))
            .setItems(options.toTypedArray()) { _, which -> actions[which]() }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    /**
     * 文件选择器 (通用 SAF)
     */
    private fun pickApkFile() {
        apkPicker.launch(arrayOf("application/vnd.android.package-archive"))
    }

    private fun onApkUriReady(uri: Uri) {
        selectedPath = uri.toString()
        binding.btnPatch.isEnabled = true
        binding.tvStatus.text = getString(R.string.apk_selected, uri.lastPathSegment ?: "APK")
    }

    // -------- 注入 --------

    private fun startPatching(apkPath: String) {
        binding.btnPatch.isEnabled = false
        binding.btnSelect.isEnabled = false
        binding.progress.visibility = android.view.View.VISIBLE
        binding.tvStatus.text = getString(R.string.status_patching)

        lifecycleScope.launch {
            try {
                val uri = if (apkPath.startsWith("/")) {
                    Uri.fromFile(File(apkPath))
                } else {
                    Uri.parse(apkPath)
                }
                val result = withContext(Dispatchers.IO) {
                    PatcherEngine.patch(this@MainActivity, uri)
                }
                patchedApkFile = result
                binding.progress.visibility = android.view.View.GONE
                binding.tvStatus.text = getString(R.string.status_done)
                binding.btnInstall.visibility = android.view.View.VISIBLE
            } catch (e: Exception) {
                binding.progress.visibility = android.view.View.GONE
                binding.tvStatus.text = "${getString(R.string.status_error)}: ${e.message}"
                binding.btnPatch.isEnabled = true
                binding.btnSelect.isEnabled = true
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    // -------- 安装 --------

    private fun checkAndInstall() {
        if (!canInstallPackages()) {
            val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES)
            intent.data = Uri.parse("package:$packageName")
            installPermission.launch(intent)
        } else {
            installPatchedApk()
        }
    }

    private fun installPatchedApk() {
        val file = patchedApkFile ?: return
        val uri = FileProvider.getUriForFile(this, "$packageName.fileprovider", file)
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/vnd.android.package-archive")
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }

    private fun canInstallPackages(): Boolean =
        Build.VERSION.SDK_INT < Build.VERSION_CODES.O || packageManager.canRequestPackageInstalls()
}
