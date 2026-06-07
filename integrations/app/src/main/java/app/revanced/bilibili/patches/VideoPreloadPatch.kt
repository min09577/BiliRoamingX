package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object VideoPreloadPatch {

    @JvmStatic
    fun shouldPreload(): Boolean {
        return Settings.VideoPreload.get()
    }

    @JvmStatic
    fun getPreloadSize(): Int {
        // Return preload buffer size in bytes
        // 0 = no preload, -1 = preload all
        return if (Settings.VideoPreload.get()) -1 else 0
    }

    @JvmStatic
    fun onPreloadDecision(url: String?, size: Long): Boolean {
        if (!Settings.VideoPreload.get()) return true

        // Allow preload
        Logger.debug { "VideoPreload: allowing preload for $url" }
        return true
    }
}
