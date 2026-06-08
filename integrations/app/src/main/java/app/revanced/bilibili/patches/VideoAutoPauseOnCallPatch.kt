package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoAutoPauseOnCallPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoAutoPauseOnCall.get()
    @JvmStatic fun onCallReceived(): Boolean = isEnabled()
}
