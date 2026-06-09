package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoWebRTCLivePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoWebRTCLive.get()
}
