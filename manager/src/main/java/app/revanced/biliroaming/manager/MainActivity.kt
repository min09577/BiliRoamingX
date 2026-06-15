package app.revanced.biliroaming.manager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.revanced.biliroaming.manager.databinding.ActivityMainBinding

/**
 * 哔哩漫游X 操作指引
 * 本 App 仅提供命令行注入的操作指南，实际注入需要在电脑上完成。
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // 隐藏不需要的交互按钮
        binding.btnSelect.isEnabled = false
        binding.btnPatch.isEnabled = false
        binding.btnInstall.visibility = android.view.View.GONE
        binding.progress.visibility = android.view.View.GONE
        
        binding.tvStatus.text = getString(R.string.guide_summary)
    }

    private lateinit var binding: ActivityMainBinding
}
