package app.revanced.bilibili.patches

import android.graphics.Typeface
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object DanmakuFontFamilyPatch {
    private const val TAG = "FontFamily"
    const val FONT_DEFAULT = 0
    const val FONT_SERIF = 1
    const val FONT_MONOSPACE = 2
    const val FONT_SANS_SERIF = 3
    @JvmStatic fun getFontPreset(): Int = Settings.DanmakuFontFamily.get().coerceIn(0, 3)
    @JvmStatic fun getTypeface(): Typeface = when (getFontPreset()) {
        FONT_SERIF -> Typeface.SERIF
        FONT_MONOSPACE -> Typeface.MONOSPACE
        FONT_SANS_SERIF -> Typeface.SANS_SERIF
        else -> Typeface.DEFAULT
    }
    @JvmStatic fun getFontDescription(): String = when (getFontPreset()) {
        FONT_SERIF -> "衬线"; FONT_MONOSPACE -> "等宽"; FONT_SANS_SERIF -> "无衬线"; else -> "默认"
    }
}
