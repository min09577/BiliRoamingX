package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedAutoNightBrightnessCustomPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedAutoNightBrightnessCustom.get()
}
