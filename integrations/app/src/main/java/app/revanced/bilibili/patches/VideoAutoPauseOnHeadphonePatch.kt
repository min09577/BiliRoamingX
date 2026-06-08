package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoAutoPauseOnHeadphonePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoAutoPauseOnHeadphone.get()
}
