package app.revanced.bilibili.settings.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.onClick

class DanmakuDisplayFragment : BaseWidgetSettingFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val (root, content, saveButton) = rootViewTemplate()

        // Danmaku opacity (0 = system default, 1-99 = custom alpha%)
        val opacity = Settings.DanmakuOpacity()
        val opacityTitle = string("biliroaming_danmaku_opacity_title")
        val opacityIndicator = string("biliroaming_danmaku_opacity_indicator")
        val opacityDefaultIndicator = string("biliroaming_default")
        val opacityItem = seekBarItem(
            name = opacityTitle,
            current = opacity,
            indicator = opacityIndicator,
            zeroIndicator = opacityDefaultIndicator,
            max = 99
        ).let { content.addView(it.first); it.second }

        // Max danmaku on screen (0 = system default)
        val maxOnScreen = Settings.DanmakuMaxOnScreen()
        val maxTitle = string("biliroaming_danmaku_max_title")
        val maxIndicator = string("biliroaming_danmaku_max_indicator")
        val maxDefaultIndicator = string("biliroaming_default")
        val maxItem = seekBarItem(
            name = maxTitle,
            current = maxOnScreen,
            indicator = maxIndicator,
            zeroIndicator = maxDefaultIndicator,
            max = 100
        ).let { content.addView(it.first); it.second }

        // Font size scale (0 = system default, 5-20 = 0.5x-2.0x)
        val fontSizeScale = Settings.DanmakuFontSizeScale()
        val fontSizeCurrent = if (fontSizeScale > 0f) (fontSizeScale * 10).toInt() else 0
        val fontSizeTitle = string("biliroaming_danmaku_font_size_title")
        val fontSizeIndicator = string("biliroaming_danmaku_font_size_indicator")
        val fontSizeDefaultIndicator = string("biliroaming_default")
        val fontSizeItem = seekBarItem(
            name = fontSizeTitle,
            current = fontSizeCurrent,
            indicator = fontSizeIndicator,
            zeroIndicator = fontSizeDefaultIndicator,
            max = 20
        ).let { content.addView(it.first); it.second }

        // Time offset (-30s to +30s, 0 = no offset)
        val timeOffset = Settings.DanmakuTimeOffset()
        val timeOffsetTitle = string("biliroaming_danmaku_time_offset_title")
        val timeOffsetIndicator = string("biliroaming_danmaku_time_offset_indicator")
        val timeOffsetDefaultIndicator = string("biliroaming_default")
        val timeOffsetItem = seekBarItem(
            name = timeOffsetTitle,
            current = timeOffset + 30,
            indicator = timeOffsetIndicator,
            zeroIndicator = timeOffsetDefaultIndicator,
            max = 60
        ).let { content.addView(it.first); it.second }

        // Pool filter (0=all, 1=normal only, 2=subtitle only, 3=special only)
        val filterPool = Settings.DanmakuFilterPool()
        val poolTitle = string("biliroaming_danmaku_filter_pool_title")
        val poolIndicator = string("biliroaming_danmaku_filter_pool_indicator")
        val poolDefaultIndicator = string("biliroaming_danmaku_filter_pool_all")
        val poolItem = seekBarItem(
            name = poolTitle,
            current = filterPool,
            indicator = poolIndicator,
            zeroIndicator = poolDefaultIndicator,
            max = 3
        ).let { content.addView(it.first); it.second }

        saveButton.onClick {
            Settings.DanmakuOpacity.save(opacityItem.progress)
            Settings.DanmakuMaxOnScreen.save(maxItem.progress)
            val fontProgress = fontSizeItem.progress
            val fontScale = if (fontProgress > 0) fontProgress / 10f else 0f
            Settings.DanmakuFontSizeScale.save(fontScale)
            Settings.DanmakuTimeOffset.save(timeOffsetItem.progress - 30)
            Settings.DanmakuFilterPool.save(poolItem.progress)
            parentFragmentManager.popBackStack()
        }

        return root
    }
}
