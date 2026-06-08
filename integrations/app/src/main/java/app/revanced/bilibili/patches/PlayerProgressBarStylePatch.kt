package app.revanced.bilibili.patches

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.SeekBar
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 播放器进度条样式自定义 — 修改进度条的颜色和样式
 * 0=默认, 1=粉色, 2=绿色, 3=蓝色, 4=橙色, 5=自定义
 */
@Keep
object PlayerProgressBarStylePatch {
    private const val TAG = "ProgressBarStyle"
    const val STYLE_DEFAULT = 0
    const val STYLE_PINK = 1
    const val STYLE_GREEN = 2
    const val STYLE_BLUE = 3
    const val STYLE_ORANGE = 4

    private val STYLE_COLORS = intArrayOf(
        0,           // default
        -0x048D67,   // bilibili pink 0xFFFB7299
        -0xB350B0,   // green 0xFF4CAF50
        -0xDE690D,   // blue 0xFF2196F3
        -0x6700       // orange 0xFFFF9800
    )

    @JvmStatic
    fun getStylePreset(): Int {
        return Settings.PlayerProgressBarStyle.get().coerceIn(0, 4)
    }

    @JvmStatic
    fun getStyleColor(): Int {
        return STYLE_COLORS[getStylePreset()]
    }

    @JvmStatic
    fun applyStyle(seekBar: SeekBar?) {
        if (seekBar == null || getStylePreset() == STYLE_DEFAULT) return
        try {
            seekBar.progressDrawable.setTint(getStyleColor())
            seekBar.thumb.setTint(getStyleColor())
            Logger.debug { "$TAG: applied style color=${Integer.toHexString(getStyleColor())}" }
        } catch (e: Throwable) {
            Logger.debug { "$TAG: failed to apply style: $e" }
        }
    }

    @JvmStatic
    fun getStyleDescription(): String {
        return when (getStylePreset()) {
            STYLE_PINK -> "粉色"
            STYLE_GREEN -> "绿色"
            STYLE_BLUE -> "蓝色"
            STYLE_ORANGE -> "橙色"
            else -> "默认"
        }
    }
}