package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerGestureDoubleTapSpeedPatch {
    /**
     * getSpeedSeconds
     */
    @JvmStatic fun getSpeedSeconds(): Int = Settings.PlayerGestureDoubleTapSpeed.get().coerceIn(0, 30)
    /**
     * getSpeedMs
     */
    @JvmStatic fun getSpeedMs(): Long = getSpeedSeconds() * 1000L
}
