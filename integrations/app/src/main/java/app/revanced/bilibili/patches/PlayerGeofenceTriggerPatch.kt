package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerGeofenceTriggerPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerGeofenceTrigger.get()
}
