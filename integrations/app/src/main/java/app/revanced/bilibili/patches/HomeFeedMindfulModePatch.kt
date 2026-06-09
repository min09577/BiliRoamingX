package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedMindfulModePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedMindfulMode.get()
}
