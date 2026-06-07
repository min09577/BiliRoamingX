package app.revanced.bilibili.patches

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object CommentReplyHighlightPatch {

    private val REPLY_COLOR = Color.parseColor("#2196F3")

    @JvmStatic
    fun highlightReplies(textView: TextView?) {
        if (!Settings.CommentReplyHighlight.get()) return
        if (textView == null) return

        try {
            val text = textView.text?.toString() ?: return
            val spannable = SpannableString(text)

            // Find reply patterns like "@username" or "回复 @username"
            val replyPattern = Regex("@\\w+")
            val matches = replyPattern.findAll(text)

            for (match in matches) {
                spannable.setSpan(
                    ForegroundColorSpan(REPLY_COLOR),
                    match.range.first,
                    match.range.last + 1,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                spannable.setSpan(
                    StyleSpan(android.graphics.Typeface.BOLD),
                    match.range.first,
                    match.range.last + 1,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            textView.text = spannable
            Logger.debug { "CommentReplyHighlight: highlighted replies" }
        } catch (e: Throwable) {
            Logger.error(e) { "CommentReplyHighlight: failed to highlight" }
        }
    }

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.CommentReplyHighlight.get()
    }
}
