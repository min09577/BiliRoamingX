package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedGPSLocationPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedGPSLocation.get()
}
