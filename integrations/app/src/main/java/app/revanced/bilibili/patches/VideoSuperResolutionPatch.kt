package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoSuperResolutionPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoSuperResolution.get()
}
