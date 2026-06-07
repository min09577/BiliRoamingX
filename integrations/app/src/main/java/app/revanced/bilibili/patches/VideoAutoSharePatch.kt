package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 视频自动分享 — 播放视频后自动将其分享链接复制到剪贴板
 * 方便快速分享给好友
 */
@Keep
object VideoAutoSharePatch {
    private const val TAG = "VideoAutoShare"
    // Share URL template
    private const val SHARE_URL_TEMPLATE = "https://bilibili.com/video/%s"
    private var lastSharedId = ""

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.VideoAutoShare.get()
    }

    @JvmStatic
    fun shouldAutoShare(videoId: String): Boolean {
        if (!isEnabled()) return false
        if (videoId == lastSharedId) return false
        lastSharedId = videoId
        Logger.debug { "$TAG: auto-sharing video $videoId" }
        return true
    }

    @JvmStatic
    fun getShareUrl(videoId: String): String {
        val url = SHARE_URL_TEMPLATE.format(videoId)
        Logger.debug { "$TAG: share URL: $url" }
        return url
    }

    @JvmStatic
    fun getShareText(videoId: String, title: String?): String {
        val url = getShareUrl(videoId)
        return if (!title.isNullOrBlank()) "$title $url" else url
    }

    @JvmStatic
    fun onShared(videoId: String) {
        lastSharedId = videoId
        Logger.debug { "$TAG: shared video $videoId" }
    }

    @JvmStatic
    fun reset() {
        lastSharedId = ""
    }
}
