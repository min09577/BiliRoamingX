package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object CommentSortByReplyPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentSortByReply.get()
    
    @JvmStatic fun compareByReply(a: Int, b: Int): Int = b - a
}
