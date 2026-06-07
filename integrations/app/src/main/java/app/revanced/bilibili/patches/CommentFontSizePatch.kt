package app.revanced.bilibili.patches

import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object CommentFontSizePatch {

    @JvmStatic
    fun applyFontSize(textView: TextView?) {
        if (textView == null) return

        try {
            val fontSize = Settings.CommentFontSize.get()
            if (fontSize > 0) {
                textView.textSize = fontSize.toFloat()
                Logger.debug { "CommentFontSize: applied size $fontSize" }
            }
        } catch (e: Throwable) {
            Logger.error(e) { "CommentFontSize: failed to apply" }
        }
    }

    @JvmStatic
    fun getFontSize(): Int {
        return Settings.CommentFontSize.get()
    }
}
