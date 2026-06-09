package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentEmojiAutoCompletePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentEmojiAutoComplete.get()
    @JvmStatic fun shouldAutoComplete(): Boolean = isEnabled()
}
