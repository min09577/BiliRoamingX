package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoAutoSkipEndingPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoAutoSkipEnding.get()
    @JvmStatic fun shouldSkipEnding(): Boolean = isEnabled()
}
