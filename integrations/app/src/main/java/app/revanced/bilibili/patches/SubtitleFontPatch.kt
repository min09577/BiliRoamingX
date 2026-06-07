package app.revanced.bilibili.patches

import android.graphics.Typeface
import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object SubtitleFontPatch {

    @JvmStatic
    fun applyFont(textView: TextView?) {
        if (textView == null) return

        try {
            val fontSetting = Settings.SubtitleFont.get()
            val typeface = when (fontSetting) {
                "bold" -> Typeface.DEFAULT_BOLD
                "serif" -> Typeface.SERIF
                "sans" -> Typeface.SANS_SERIF
                "monospace" -> Typeface.MONOSPACE
                else -> return // default, don't change
            }
            textView.typeface = typeface
            Logger.debug { "SubtitleFont: applied $fontSetting" }
        } catch (e: Throwable) {
            Logger.error(e) { "SubtitleFont: failed to apply" }
        }
    }

    @JvmStatic
    fun getFontName(): String {
        return Settings.SubtitleFont.get()
    }
}
