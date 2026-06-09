package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerScreenshotSharePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerScreenshotShare.get()
    @JvmStatic fun shouldShare(): Boolean = isEnabled()
}
