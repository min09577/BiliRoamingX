package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentAutoDeletePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentAutoDelete.get()
    @JvmStatic fun shouldAutoDelete(): Boolean = isEnabled()
}
