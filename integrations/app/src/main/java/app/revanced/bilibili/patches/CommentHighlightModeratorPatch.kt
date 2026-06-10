package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentHighlightModeratorPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentHighlightModerator.get()
    /**
     * isModerator
     */
    @JvmStatic fun isModerator(role: Int): Boolean = isEnabled() && role >= 2
}
