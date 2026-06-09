package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedGeneticAlgorithmPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedGeneticAlgorithm.get()
}
