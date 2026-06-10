package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerControlAutoHideTimeoutPatch {
    /**
     * getTimeoutSeconds
     */
    @JvmStatic fun getTimeoutSeconds(): Int = Settings.PlayerControlAutoHideTimeout.get().coerceIn(1, 10)
    /**
     * getTimeoutMs
     */
    @JvmStatic fun getTimeoutMs(): Long = getTimeoutSeconds() * 1000L
}
