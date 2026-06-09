package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoScannerModePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoScannerMode.get()
}
