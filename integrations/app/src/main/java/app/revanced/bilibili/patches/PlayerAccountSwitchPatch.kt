package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerAccountSwitchPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerAccountSwitch.get()
}
