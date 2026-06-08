package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoSmartResolutionPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoSmartResolution.get()
    @JvmStatic fun getResolution(screenWidth: Int): Int = when {
        screenWidth >= 2560 -> 127; screenWidth >= 1920 -> 80
        screenWidth >= 1280 -> 64; else -> 32
    }
}
