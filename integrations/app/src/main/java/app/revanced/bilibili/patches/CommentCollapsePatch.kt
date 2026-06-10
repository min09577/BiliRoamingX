package app.revanced.bilibili.patches

import android.view.View
import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object CommentCollapsePatch {

    @JvmStatic
    fun onCommentBind(textView: TextView?) {
        if (!Settings.CommentCollapse.get()) return
        if (textView == null) return

        try {
            val maxLines = Settings.CommentCollapseLines.get()
            if (maxLines <= 0) return

            // Set max lines to collapse
            textView.maxLines = maxLines
            textView.ellipsize = android.text.TextUtils.TruncateAt.END

            // Add click to expand
            textView.setOnClickListener {
                if (textView.maxLines == maxLines) {
                    textView.maxLines = Integer.MAX_VALUE
                    textView.ellipsize = null
                } else {
                    textView.maxLines = maxLines
                    textView.ellipsize = android.text.TextUtils.TruncateAt.END
                }
            }

            Logger.debug { "CommentCollapse: applied collapse with maxLines=$maxLines" }
        } catch (e: Throwable) {
            Logger.error(e) { "CommentCollapse: failed to apply" }
        }
    }

    @JvmStatic
    fun getMaxLines(): Int {
        return Settings.CommentCollapseLines.get()
    }
}
