package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoSmartPausePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoSmartPause.get()
    @JvmStatic fun shouldSmartPause(): Boolean = isEnabled()
}
