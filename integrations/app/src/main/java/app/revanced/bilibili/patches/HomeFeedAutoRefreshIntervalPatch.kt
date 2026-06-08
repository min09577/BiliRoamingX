package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedAutoRefreshIntervalPatch {
    @JvmStatic fun getIntervalSeconds(): Int = Settings.HomeFeedAutoRefreshInterval.get().coerceIn(0, 3600)
    @JvmStatic fun getIntervalMs(): Long = getIntervalSeconds() * 1000L
    @JvmStatic fun isEnabled(): Boolean = getIntervalSeconds() > 0
}
