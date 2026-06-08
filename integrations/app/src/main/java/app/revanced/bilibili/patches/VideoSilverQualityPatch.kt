package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoSilverQualityPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoSilverQuality.get()
}
