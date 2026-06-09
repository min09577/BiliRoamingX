package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerNeuralUpscalePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerNeuralUpscale.get()
}
