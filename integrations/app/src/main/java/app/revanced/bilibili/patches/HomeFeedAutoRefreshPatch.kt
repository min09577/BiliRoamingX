package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedAutoRefreshPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedAutoRefresh.get()
    @JvmStatic fun shouldAutoRefresh(): Boolean = isEnabled()
}
