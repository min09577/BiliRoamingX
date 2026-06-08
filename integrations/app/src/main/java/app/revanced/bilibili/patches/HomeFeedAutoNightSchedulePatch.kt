package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedAutoNightSchedulePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedAutoNightSchedule.get()
}
