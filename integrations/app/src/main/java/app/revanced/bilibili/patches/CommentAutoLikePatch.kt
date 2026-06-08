package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object CommentAutoLikePatch {
    private const val TAG = "CommentAutoLike"
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentAutoLike.get()
    @JvmStatic fun shouldAutoLike(likeCount: Int): Boolean {
        if (!isEnabled()) return false
        return likeCount >= 10
    }
}
