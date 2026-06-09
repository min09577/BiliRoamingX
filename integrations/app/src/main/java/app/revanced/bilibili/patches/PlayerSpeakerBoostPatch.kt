package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerSpeakerBoostPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerSpeakerBoost.get()
}
