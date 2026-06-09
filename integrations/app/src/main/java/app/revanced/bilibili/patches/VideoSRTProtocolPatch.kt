package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoSRTProtocolPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoSRTProtocol.get()
}
