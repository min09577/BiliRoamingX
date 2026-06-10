package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentAutoLikePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentAutoLike.get()
    /**
     * shouldAutoLike
     */
    @JvmStatic fun shouldAutoLike(): Boolean = isEnabled()
}
