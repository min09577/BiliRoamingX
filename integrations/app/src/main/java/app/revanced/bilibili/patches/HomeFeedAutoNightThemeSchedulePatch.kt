package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedAutoNightThemeSchedulePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedAutoNightThemeSchedule.get()
}
