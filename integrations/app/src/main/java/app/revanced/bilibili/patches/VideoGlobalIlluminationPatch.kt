package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoGlobalIlluminationPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoGlobalIllumination.get()
}
