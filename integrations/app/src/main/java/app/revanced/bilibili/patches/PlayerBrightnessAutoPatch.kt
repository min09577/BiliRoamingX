package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerBrightnessAutoPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerBrightnessAuto.get()
    /**
     * shouldAutoBrightness
     */
    @JvmStatic fun shouldAutoBrightness(): Boolean = isEnabled()
}
