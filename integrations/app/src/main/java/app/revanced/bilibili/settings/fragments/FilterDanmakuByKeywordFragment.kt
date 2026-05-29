package app.revanced.bilibili.settings.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Toasts
import app.revanced.bilibili.utils.dp
import app.revanced.bilibili.utils.onClick

class FilterDanmakuByKeywordFragment : BaseWidgetSettingFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val (root, content, saveButton) = rootViewTemplate()
        val (keywordGroup, regexSwitch) = content.addKeywordGroup(
            string("biliroaming_keyword_group_name_content"), 40.dp, true
        )
        regexSwitch.isChecked = Settings.DanmakuFilterRegexMode()
        Settings.DanmakuFilterKeywords().forEach {
            keywordGroup.addView(keywordInputItem(keywordGroup, it).first)
        }

        saveButton.onClick {
            val keywords = keywordGroup.getKeywords()
            val regexMode = regexSwitch.isChecked
            if (regexMode && keywords.runCatching { forEach { it.toRegex() } }.isFailure) {
                Toasts.showShortWithId("biliroaming_invalid_regex")
                return@onClick
            }
            Settings.DanmakuFilterKeywords.save(keywords)
            Settings.DanmakuFilterRegexMode.save(regexMode)
            parentFragmentManager.popBackStack()
        }

        return root
    }
}
