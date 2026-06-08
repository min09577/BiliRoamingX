package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoAutoPauseOnBatteryPatch {
    @JvmStatic fun getBatteryThreshold(): Int = Settings.VideoAutoPauseOnBattery.get().coerceIn(0, 50)
    @JvmStatic fun shouldPause(batteryLevel: Int): Boolean = getBatteryThreshold() > 0 && batteryLevel <= getBatteryThreshold()
}
