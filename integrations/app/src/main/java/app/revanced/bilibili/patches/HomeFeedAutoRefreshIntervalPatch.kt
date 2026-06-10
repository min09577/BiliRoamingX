package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedAutoRefreshIntervalPatch {
    /**
     * getIntervalSeconds
     */
    @JvmStatic fun getIntervalSeconds(): Int = Settings.HomeFeedAutoRefreshInterval.get().coerceIn(0, 3600)
    /**
     * getIntervalMs
     */
    @JvmStatic fun getIntervalMs(): Long = getIntervalSeconds() * 1000L
    @JvmStatic fun isEnabled(): Boolean = getIntervalSeconds() > 0
}
