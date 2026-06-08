package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerGestureDoubleTapSpeedPatch {
    @JvmStatic fun getSpeedSeconds(): Int = Settings.PlayerGestureDoubleTapSpeed.get().coerceIn(0, 30)
    @JvmStatic fun getSpeedMs(): Long = getSpeedSeconds() * 1000L
}
