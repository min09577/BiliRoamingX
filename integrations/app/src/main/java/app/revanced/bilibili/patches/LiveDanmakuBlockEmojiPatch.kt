package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object LiveDanmakuBlockEmojiPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.LiveDanmakuBlockEmoji.get()
    @JvmStatic fun shouldBlock(text: String): Boolean = isEnabled() && text.length <= 3 && text.all { !it.isLetterOrDigit() }
}
