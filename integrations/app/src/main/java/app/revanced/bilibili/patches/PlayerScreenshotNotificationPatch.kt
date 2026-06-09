package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerScreenshotNotificationPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerScreenshotNotification.get()
    @JvmStatic fun shouldNotify(): Boolean = isEnabled()
}
