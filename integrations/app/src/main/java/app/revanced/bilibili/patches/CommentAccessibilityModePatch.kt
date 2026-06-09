package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentAccessibilityModePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentAccessibilityMode.get()
    @JvmStatic fun getMode(): Int = if (isEnabled()) 1 else 0
}
