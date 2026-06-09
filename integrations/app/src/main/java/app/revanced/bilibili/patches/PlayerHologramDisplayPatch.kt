package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerHologramDisplayPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerHologramDisplay.get()
}
