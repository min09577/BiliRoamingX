package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentAutoExpandShortPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentAutoExpandShort.get()
    @JvmStatic fun shouldExpand(text: String): Boolean = isEnabled() && text.length < 50
}
