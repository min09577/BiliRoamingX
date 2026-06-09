package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentSpamFilterPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentSpamFilter.get()
}
