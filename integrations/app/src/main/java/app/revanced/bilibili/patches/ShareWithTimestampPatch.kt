package app.revanced.bilibili.patches

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.Toasts

@Keep
object ShareWithTimestampPatch {

    @JvmStatic
    fun modifyShareUrl(activity: Activity?, bvid: String?, currentPositionMs: Long, originalUrl: String?): String? {
        if (!Settings.ShareWithTimestamp.get()) return originalUrl
        if (bvid == null || currentPositionMs <= 0) return originalUrl

        try {
            // Build URL with timestamp parameter (t=seconds)
            val timestampSec = currentPositionMs / 1000
            val baseUrl = originalUrl ?: "https://www.bilibili.com/video/$bvid"
            val timestampUrl = if (baseUrl.contains("?")) {
                "$baseUrl&t=${timestampSec}s"
            } else {
                "$baseUrl?t=${timestampSec}s"
            }

            Logger.debug { "ShareWithTimestampPatch: modified URL with t=${timestampSec}s" }
            return timestampUrl
        } catch (e: Throwable) {
            Logger.error(e) { "ShareWithTimestampPatch: failed to modify share URL" }
            return originalUrl
        }
    }

    @JvmStatic
    fun copyTimestampLink(activity: Activity?, bvid: String?, currentPositionMs: Long) {
        if (!Settings.ShareWithTimestamp.get()) return
        if (bvid == null || currentPositionMs <= 0) return

        try {
            val timestampSec = currentPositionMs / 1000
            val url = "https://www.bilibili.com/video/$bvid?t=${timestampSec}s"

            val clipboard = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
            clipboard?.setPrimaryClip(ClipData.newPlainText("bilibili_timestamp", url))
            Toasts.showShort("已复制带时间点的链接: ${formatTime(currentPositionMs)}")
            Logger.debug { "ShareWithTimestampPatch: copied timestamp link" }
        } catch (e: Throwable) {
            Logger.error(e) { "ShareWithTimestampPatch: failed to copy link" }
        }
    }

    private fun formatTime(ms: Long): String {
        val totalSeconds = ms / 1000
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60
        return if (hours > 0) {
            String.format("%d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format("%02d:%02d", minutes, seconds)
        }
    }
}
