package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object CommentHighlightNewPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentHighlightNew.get()
    
    @JvmStatic fun isNewComment(timestamp: Long): Boolean {
        if (!isEnabled()) return false
        val now = System.currentTimeMillis() / 1000
        return (now - timestamp) < 3600 // within 1 hour
    }
}
