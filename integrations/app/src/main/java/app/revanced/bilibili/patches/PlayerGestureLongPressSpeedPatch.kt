package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerGestureLongPressSpeedPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerGestureLongPressSpeed.get()
    /**
     * getSpeed
     */
    @JvmStatic fun getSpeed(): Float = if (isEnabled()) 2.0f else 1.0f
}
