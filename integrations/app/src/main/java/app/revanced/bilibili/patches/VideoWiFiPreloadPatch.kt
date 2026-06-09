package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoWiFiPreloadPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoWiFiPreload.get()
}
