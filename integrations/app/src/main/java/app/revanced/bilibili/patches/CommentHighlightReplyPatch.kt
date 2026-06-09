package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object CommentHighlightReplyPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentHighlightReply.get()
    
    @JvmStatic fun hasReplies(replyCount: Int): Boolean {
        if (!isEnabled()) return false
        return replyCount > 0
    }
}
