package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedSmartHomeConnectPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedSmartHomeConnect.get()
}
