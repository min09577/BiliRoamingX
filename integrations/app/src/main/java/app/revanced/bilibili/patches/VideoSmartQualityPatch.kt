package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoSmartQualityPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoSmartQuality.get()
}
