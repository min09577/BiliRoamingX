package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedAnalyticsDisplayPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedAnalyticsDisplay.get()
}
