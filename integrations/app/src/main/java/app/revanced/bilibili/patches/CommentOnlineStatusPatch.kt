package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentOnlineStatusPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentOnlineStatus.get()
}
