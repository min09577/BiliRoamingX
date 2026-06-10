package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicAutoRefreshIntervalPatch {
    /**
     * getIntervalSeconds
     */
    @JvmStatic fun getIntervalSeconds(): Int = Settings.DynamicAutoRefreshInterval.get().coerceIn(0, 3600)
    /**
     * getIntervalMs
     */
    @JvmStatic fun getIntervalMs(): Long = getIntervalSeconds() * 1000L
    @JvmStatic fun isEnabled(): Boolean = getIntervalSeconds() > 0
}
