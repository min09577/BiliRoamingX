package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentHighlightNewPatch {
    private const val ONE_HOUR_SECONDS = 3600L
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentHighlightNew.get()
    /**
     * isNewComment
     */
    @JvmStatic fun isNewComment(ts: Long): Boolean = isEnabled() && (System.currentTimeMillis()/1000 - ts) < ONE_HOUR_SECONDS
}
