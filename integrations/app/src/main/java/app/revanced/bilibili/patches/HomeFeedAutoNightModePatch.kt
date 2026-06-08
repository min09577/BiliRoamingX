package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedAutoNightModePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedAutoNightMode.get()
}
