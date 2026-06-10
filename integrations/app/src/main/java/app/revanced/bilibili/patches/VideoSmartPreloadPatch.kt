package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object VideoSmartPreloadPatch {
    private const val TAG = "SmartPreload"
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoSmartPreload.get()
    @JvmStatic fun getPreloadSize(networkType: Int): Int {
        if (!isEnabled()) return 0
        return when (networkType) {
            1 -> 30  // WiFi: 30MB
            0 -> 10  // Mobile: 10MB
            else -> 5
        }
    }
}
