package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentHighlightReplyPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentHighlightReply.get()
    @JvmStatic fun hasReplies(count: Int): Boolean = isEnabled() && count > 0
}
