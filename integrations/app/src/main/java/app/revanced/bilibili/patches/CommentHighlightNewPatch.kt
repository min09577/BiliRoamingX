package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentHighlightNewPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentHighlightNew.get()
    @JvmStatic fun isNewComment(ts: Long): Boolean = isEnabled() && (System.currentTimeMillis()/1000 - ts) < 3600
}
