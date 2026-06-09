package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoClothSimulationPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoClothSimulation.get()
}
