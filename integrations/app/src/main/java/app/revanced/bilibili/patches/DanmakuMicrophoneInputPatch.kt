package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuMicrophoneInputPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuMicrophoneInput.get()
}
