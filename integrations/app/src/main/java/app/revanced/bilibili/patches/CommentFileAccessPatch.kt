package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentFileAccessPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentFileAccess.get()
}
