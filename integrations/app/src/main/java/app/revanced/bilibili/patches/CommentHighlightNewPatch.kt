package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentHighlightNewPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentHighlightNew.get()
}
