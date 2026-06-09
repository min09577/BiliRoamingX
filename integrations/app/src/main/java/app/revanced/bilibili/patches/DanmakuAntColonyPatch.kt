package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuAntColonyPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuAntColony.get()
}
