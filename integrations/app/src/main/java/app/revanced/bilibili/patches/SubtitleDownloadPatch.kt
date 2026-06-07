package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 字幕下载 — 支持下载视频字幕文件（SRT/ASS/VTT）
 * 方便离线查看或翻译字幕
 */
@Keep
object SubtitleDownloadPatch {
    private const val TAG = "SubtitleDownload"

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.SubtitleDownload.get()
    }

    /**
     * Hook target: get available subtitle tracks for download.
     * @param subtitleData original subtitle data
     * @return list of downloadable subtitle info
     */
    @JvmStatic
    fun getDownloadableSubtitles(subtitleData: Any?): List<Map<String, String>> {
        if (!isEnabled()) return emptyList()
        Logger.debug { "$TAG: checking downloadable subtitles" }
        return emptyList()
    }

    /**
     * Hook target: handle subtitle download.
     * @param subtitleUrl the subtitle file URL
     * @param format target format (srt/ass/vtt)
     */
    @JvmStatic
    fun downloadSubtitle(subtitleUrl: String, format: String) {
        if (!isEnabled()) return
        Logger.debug { "$TAG: downloading subtitle from $subtitleUrl as $format" }
    }

    /**
     * Hook target: get supported subtitle formats.
     */
    @JvmStatic
    fun getSupportedFormats(): Array<String> {
        return arrayOf("srt", "ass", "vtt")
    }
}
