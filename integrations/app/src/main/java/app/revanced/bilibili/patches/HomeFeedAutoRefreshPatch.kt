package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedAutoRefreshPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedAutoRefresh.get()
    @JvmStatic fun getRefreshIntervalMs(): Long = if (isEnabled()) 300_000L else 0L
}
