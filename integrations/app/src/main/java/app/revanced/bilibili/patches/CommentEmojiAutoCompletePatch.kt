package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentEmojiAutoCompletePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentEmojiAutoComplete.get()
    /**
     * shouldAutoComplete
     */
    @JvmStatic fun shouldAutoComplete(): Boolean = isEnabled()
}
