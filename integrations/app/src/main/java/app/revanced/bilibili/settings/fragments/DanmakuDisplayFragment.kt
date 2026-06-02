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

        // Danmaku speed (0 = system default, 1-10 = custom speed)
        val danmakuSpeed = Settings.DanmakuSpeed()
        val speedTitle = string("biliroaming_danmaku_speed_title")
        val speedSummary = string("biliroaming_danmaku_speed_summary")
        val speedItem = seekBarItem(
            name = speedTitle,
            current = danmakuSpeed,
            indicator = "%%d",
            zeroIndicator = "默认",
            max = 10
        ).let { content.addView(it.first); it.second }

        // Danmaku area (0 = system default, 10-100 = custom area%)
        val danmakuArea = Settings.DanmakuArea()
        val areaTitle = string("biliroaming_danmaku_area_title")
        val areaSummary = string("biliroaming_danmaku_area_summary")
        val areaItem = seekBarItem(
            name = areaTitle,
            current = danmakuArea,
            indicator = "%%d%%",
            zeroIndicator = "默认",
            max = 100
        ).let { content.addView(it.first); it.second }

        // Highlight keywords (comma-separated)
        val highlightKeywords = Settings.DanmakuHighlightKeywords()
        val highlightTitle = string("biliroaming_danmaku_highlight_title")
        val highlightSummary = string("biliroaming_danmaku_highlight_summary")
        val highlightItem = textInputWithButtonItem(
            name = highlightTitle,
            text = highlightKeywords.joinToString(","),
            hint = "关键词1,关键词2,...",
            buttonName = "清除"
        ) { editText ->
            editText.setText("")
        }.let { content.addView(it.first); it.second }

        // Highlight color (predefined options)
        val highlightColors = intArrayOf(
            0xFFFF0000.toInt(), // Red
            0xFF00FF00.toInt(), // Green
            0xFF0000FF.toInt(), // Blue
            0xFFFFFF00.toInt(), // Yellow
            0xFFFF00FF.toInt(), // Magenta
            0xFF00FFFF.toInt(), // Cyan
            0xFFFFA500.toInt(), // Orange
        )
        val highlightColorNames = arrayOf("红色", "绿色", "蓝色", "黄色", "品红", "青色", "橙色")
        val currentColor = Settings.DanmakuHighlightColor()
        val colorIndex = highlightColors.indexOf(currentColor).coerceAtLeast(0)
        val colorTitle = string("biliroaming_danmaku_highlight_color_title")
        val colorSummary = string("biliroaming_danmaku_highlight_color_summary")
        val colorItem = textInputItem(
            name = "$colorTitle (${highlightColorNames[colorIndex]})"
        ).let {
            content.addView(it.first)
            it.second.setText(colorIndex.toString())
            it.second
        }

        saveButton.onClick {
            Settings.DanmakuOpacity.save(opacityItem.progress)
            Settings.DanmakuMaxOnScreen.save(maxItem.progress)
            val fontProgress = fontSizeItem.progress
            val fontScale = if (fontProgress > 0) fontProgress / 10f else 0f
            Settings.DanmakuFontSizeScale.save(fontScale)
            Settings.DanmakuTimeOffset.save(timeOffsetItem.progress - 30)
            Settings.DanmakuFilterPool.save(poolItem.progress)
            Settings.DanmakuSpeed.save(speedItem.progress)
            Settings.DanmakuArea.save(areaItem.progress)
            val keywords = highlightItem.text.toString()
                .split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .toSet()
            Settings.DanmakuHighlightKeywords.save(keywords)
            val selectedColorIndex = colorItem.text.toString().toIntOrNull() ?: 0
            if (selectedColorIndex in highlightColors.indices) {
                Settings.DanmakuHighlightColor.save(highlightColors[selectedColorIndex])
            }
            parentFragmentManager.popBackStack()
        }

        return root
    }
}
