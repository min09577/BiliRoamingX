package app.revanced.bilibili.patches

import android.graphics.Paint
import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 直播弹幕字体 — 自定义直播弹幕的字体大小和样式
 * 与普通视频弹幕字体独立设置
 */
@Keep
object LiveDanmakuFontPatch {
    private const val TAG = "LiveDanmakuFont"
    const val FONT_SMALL = 0
    const val FONT_MEDIUM = 1
    const val FONT_LARGE = 2
    const val FONT_EXTRA_LARGE = 3
    // Scale factors
    private val SCALE_FACTORS = floatArrayOf(0.8f, 1.0f, 1.2f, 1.5f)

    @JvmStatic
    fun getFontPreset(): Int {
        return Settings.LiveDanmakuFont.get().coerceIn(0, 3)
    }

    @JvmStatic
    fun getFontScale(): Float {
        return SCALE_FACTORS[getFontPreset()]
    }

    @JvmStatic
    fun applyFont(textView: TextView) {
        val scale = getFontScale()
        if (scale == 1.0f) return
        try {
            textView.textSize = textView.textSize * scale
            Logger.debug { "$TAG: applied font scale $scale" }
        } catch (e: Throwable) {
            Logger.debug { "$TAG: failed to apply font: $e" }
        }
    }

    @JvmStatic
    fun applyPaintFont(paint: Paint) {
        val scale = getFontScale()
        if (scale == 1.0f) return
        paint.textSize = paint.textSize * scale
    }

    @JvmStatic
    fun getFontDescription(): String {
        return when (getFontPreset()) {
            FONT_SMALL -> "小"
            FONT_LARGE -> "大"
            FONT_EXTRA_LARGE -> "特大"
            else -> "默认"
        }
    }
}
