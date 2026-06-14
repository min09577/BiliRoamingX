package app.revanced.biliroaming.manager

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
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
    private var selectedApkUri: Uri? = null

    private val apkPicker = registerForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let {
            selectedApkUri = it
            binding.btnPatch.isEnabled = true
            binding.tvStatus.text = getString(R.string.apk_selected, 
                it.lastPathSegment ?: "APK")
        }
    }

    private val installPermission = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && 
            packageManager.canRequestPackageInstalls()) {
            installPatchedApk()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelect.setOnClickListener {
            apkPicker.launch(arrayOf(
                "application/vnd.android.package-archive",
                "application/octet-stream"
            ))
        }

        binding.btnPatch.setOnClickListener {
            selectedApkUri?.let { uri ->
                startPatching(uri)
            }
        }

        binding.btnInstall.setOnClickListener {
            checkAndInstall()
        }
    }

    private fun startPatching(apkUri: Uri) {
        binding.btnPatch.isEnabled = false
        binding.btnSelect.isEnabled = false
        binding.progress.visibility = android.view.View.VISIBLE
        binding.tvStatus.text = getString(R.string.status_patching)

        lifecycleScope.launch {
            try {
                val patchedFile = withContext(Dispatchers.IO) {
                    PatcherEngine.patch(this@MainActivity, apkUri)
                }

                patchedApkFile = patchedFile
                binding.progress.visibility = android.view.View.GONE
                binding.tvStatus.text = getString(R.string.status_done)
                binding.btnInstall.visibility = android.view.View.VISIBLE
            } catch (e: Exception) {
                binding.progress.visibility = android.view.View.GONE
                binding.tvStatus.text = "${getString(R.string.status_error)}: ${e.message}"
                binding.btnPatch.isEnabled = true
                binding.btnSelect.isEnabled = true
            }
        }
    }

    private var patchedApkFile: File? = null

    private fun checkAndInstall() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            !packageManager.canRequestPackageInstalls()) {
            val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES)
            intent.data = Uri.parse("package:$packageName")
            installPermission.launch(intent)
        } else {
            installPatchedApk()
        }
    }

    private fun installPatchedApk() {
        val file = patchedApkFile ?: return
        val uri = FileProvider.getUriForFile(
            this, "$packageName.fileprovider", file
        )
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/vnd.android.package-archive")
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }
}
