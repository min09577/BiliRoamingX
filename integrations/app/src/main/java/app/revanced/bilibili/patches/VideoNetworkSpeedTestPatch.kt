package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoNetworkSpeedTestPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoNetworkSpeedTest.get()
}
