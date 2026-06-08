package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedPerformanceModePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedPerformanceMode.get()
}
