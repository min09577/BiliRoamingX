package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedAutoNightPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedAutoNight.get()
    @JvmStatic fun shouldAutoNight(): Boolean = isEnabled()
}
