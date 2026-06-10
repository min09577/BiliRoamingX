package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoAutoPauseOnBatteryPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoAutoPauseOnBattery.get()
    /**
     * shouldPause
     */
    @JvmStatic fun shouldPause(level: Int): Boolean = isEnabled() && level <= 15
}
