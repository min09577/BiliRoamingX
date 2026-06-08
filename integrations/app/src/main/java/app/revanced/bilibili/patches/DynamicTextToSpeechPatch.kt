package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicTextToSpeechPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicTextToSpeech.get()
}
