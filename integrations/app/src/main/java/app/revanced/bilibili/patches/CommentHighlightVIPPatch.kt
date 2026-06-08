package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentHighlightVIPPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentHighlightVIP.get()
}
