package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoSmartBitratePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoSmartBitrate.get()
    @JvmStatic fun getBitrate(bandwidth: Int): Int = when {
        bandwidth >= 10000 -> 4000; bandwidth >= 5000 -> 2000
        bandwidth >= 2000 -> 1000; else -> 500
    }
}
