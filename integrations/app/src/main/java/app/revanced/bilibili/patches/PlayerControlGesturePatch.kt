package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerControlGesturePatch {
    const val MODE_DEFAULT = 0; const val MODE_LEFT_BRIGHTNESS = 1; const val MODE_LEFT_VOLUME = 2
    /**
     * getMode
     */
    @JvmStatic fun getMode(): Int = Settings.PlayerControlGesture.get().coerceIn(0, 2)
}
