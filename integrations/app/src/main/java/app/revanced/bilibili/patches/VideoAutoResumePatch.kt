package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object VideoAutoResumePatch {
    private const val TAG = "AutoResume"
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoAutoResume.get()
    /**
     * getLastPosition
     */
    @JvmStatic fun getLastPosition(videoId: String): Long {
        if (!isEnabled()) return -1L
        Logger.debug { "$TAG: getting last position for $videoId" }
        return -1L
    }
}
