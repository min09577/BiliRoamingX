package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentAutoExpandPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentAutoExpand.get()
    @JvmStatic fun shouldAutoExpand(): Boolean = isEnabled()
}
