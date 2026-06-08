package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerBatteryTemperaturePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerBatteryTemperature.get()
}
