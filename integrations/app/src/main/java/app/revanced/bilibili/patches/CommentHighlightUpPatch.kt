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
object CommentHighlightUpPatch {

    private val UP_COLOR = Color.parseColor("#FB7299") // Bilibili pink

    @JvmStatic
    fun highlightUpComment(textView: TextView?, isUp: Boolean, upName: String?) {
        if (!Settings.CommentHighlightUp.get()) return
        if (!isUp || textView == null) return

        try {
            val text = textView.text?.toString() ?: return
            val spannable = SpannableString(text)

            // Add UP badge at the beginning
            val badge = "UP "
            val fullText = "$badge$text"
            val fullSpannable = SpannableString(fullText)

            // Style the UP badge
            fullSpannable.setSpan(
                ForegroundColorSpan(UP_COLOR),
                0, badge.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            fullSpannable.setSpan(
                StyleSpan(android.graphics.Typeface.BOLD),
                0, badge.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            // Highlight the comment text with a subtle background
            fullSpannable.setSpan(
                ForegroundColorSpan(UP_COLOR),
                badge.length, fullText.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            textView.text = fullSpannable
            Logger.debug { "CommentHighlightUp: highlighted UP comment" }
        } catch (e: Throwable) {
            Logger.error(e) { "CommentHighlightUp: failed to highlight" }
        }
    }

    @JvmStatic
    fun isUpComment(upMid: Long, currentMid: Long): Boolean {
        return upMid > 0 && upMid == currentMid
    }
}
