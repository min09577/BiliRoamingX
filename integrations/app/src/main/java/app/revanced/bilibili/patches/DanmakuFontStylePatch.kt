package app.revanced.bilibili.patches

import android.graphics.Paint
import android.graphics.Typeface
import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 弹幕字体样式 — 自定义弹幕文字的字体样式
 * 支持：默认、粗体、斜体、粗斜体
 */
@Keep
object DanmakuFontStylePatch {
    private const val TAG = "DanmakuFontStyle"
    // Style constants
    const val STYLE_DEFAULT = 0
    const val STYLE_BOLD = 1
    const val STYLE_ITALIC = 2
    const val STYLE_BOLD_ITALIC = 3

    /**
     * Get the configured font style.
     */
    @JvmStatic
    fun getFontStyle(): Int {
        return Settings.DanmakuFontStyle.get().coerceIn(0, 3)
    }

    /**
     * Hook target: apply font style to danmaku text view.
     * Called when creating/reusing danmaku views.
     */
    @JvmStatic
    fun applyFontStyle(textView: TextView) {
        val style = getFontStyle()
        if (style == STYLE_DEFAULT) return

        try {
            textView.typeface = when (style) {
                STYLE_BOLD -> Typeface.DEFAULT_BOLD
                STYLE_ITALIC -> Typeface.create(Typeface.DEFAULT, Typeface.ITALIC)
                STYLE_BOLD_ITALIC -> Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC)
                else -> Typeface.DEFAULT
            }
            Logger.debug { "$TAG: applied style $style" }
        } catch (e: Throwable) {
            Logger.debug { "$TAG: failed to apply style: $e" }
        }
    }

    /**
     * Hook target: apply font style via Paint object.
     * Used for custom danmaku renderers.
     */
    @JvmStatic
    fun applyPaintStyle(paint: Paint) {
        val style = getFontStyle()
        if (style == STYLE_DEFAULT) return

        try {
            paint.typeface = when (style) {
                STYLE_BOLD -> Typeface.DEFAULT_BOLD
                STYLE_ITALIC -> Typeface.create(Typeface.DEFAULT, Typeface.ITALIC)
                STYLE_BOLD_ITALIC -> Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC)
                else -> Typeface.DEFAULT
            }
            paint.isFakeBoldText = style == STYLE_BOLD || style == STYLE_BOLD_ITALIC
            Logger.debug { "$TAG: applied paint style $style" }
        } catch (e: Throwable) {
            Logger.debug { "$TAG: failed to apply paint style: $e" }
        }
    }

    /**
     * Get style name for UI display.
     */
    @JvmStatic
    fun getStyleName(): String {
        return when (getFontStyle()) {
            STYLE_BOLD -> "粗体"
            STYLE_ITALIC -> "斜体"
            STYLE_BOLD_ITALIC -> "粗斜体"
            else -> "默认"
        }
    }
}
