package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoAutoPauseOnLockPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoAutoPauseOnLock.get()
    @JvmStatic fun onScreenLocked(): Boolean = isEnabled()
}
