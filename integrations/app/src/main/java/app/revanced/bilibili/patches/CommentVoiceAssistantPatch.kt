package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentVoiceAssistantPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentVoiceAssistant.get()
}
