package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerWearableControlPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerWearableControl.get()
}
