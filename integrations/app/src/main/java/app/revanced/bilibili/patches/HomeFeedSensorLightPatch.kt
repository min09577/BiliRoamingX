package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedSensorLightPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedSensorLight.get()
}
