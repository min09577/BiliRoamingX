package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerLongPressSpeedPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerLongPressSpeed.get() != 0f
    /**
     * getSpeed
     */
    @JvmStatic fun getSpeed(): Float = Settings.PlayerLongPressSpeed.get()
}
