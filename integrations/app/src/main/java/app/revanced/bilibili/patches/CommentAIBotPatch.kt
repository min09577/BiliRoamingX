package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentAIBotPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentAIBot.get()
}
