package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object LiveDanmakuBlockEmojiPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.LiveDanmakuBlockEmoji.get()
    @JvmStatic fun shouldBlockEmoji(): Boolean = isEnabled()
}
