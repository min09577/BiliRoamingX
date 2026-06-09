package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerWebUSBControlPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerWebUSBControl.get()
}
