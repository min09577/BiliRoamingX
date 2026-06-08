package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoAutoPauseOnNotificationPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoAutoPauseOnNotification.get()
}
