package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 视频自动下载 — 收藏视频后自动下载到本地
 */
@Keep
object VideoAutoDownloadPatch {
    private const val TAG = "VideoAutoDownload"

    @JvmStatic
    fun isEnabled(): Boolean = Settings.VideoAutoDownload.get()

    @JvmStatic
    fun shouldAutoDownload(videoId: String): Boolean {
        if (!isEnabled()) return false
        Logger.debug { "$TAG: auto downloading $videoId" }
        return true
    }
}
