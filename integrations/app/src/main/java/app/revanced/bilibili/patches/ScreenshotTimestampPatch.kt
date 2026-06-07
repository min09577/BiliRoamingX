package app.revanced.bilibili.patches

import android.graphics.*
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object ScreenshotTimestampPatch {

    @JvmStatic
    fun addTimestampToBitmap(bitmap: Bitmap, currentTimeMs: Long, videoTitle: String?): Bitmap? {
        if (!Settings.ScreenshotTimestamp.get()) return null

        try {
            val result = bitmap.copy(Bitmap.Config.ARGB_8888, true) ?: return null
            val canvas = Canvas(result)

            val paint = Paint().apply {
                color = Color.WHITE
                textSize = 24f
                isAntiAlias = true
                setShadowLayer(2f, 1f, 1f, Color.BLACK)
                typeface = Typeface.DEFAULT_BOLD
            }

            // Format timestamp
            val timestamp = formatTime(currentTimeMs)
            val text = "⏱ $timestamp"

            // Draw in bottom-right corner
            val bounds = Rect()
            paint.getTextBounds(text, 0, text.length, bounds)
            val x = result.width - bounds.width() - 20f
            val y = result.height - 20f

            canvas.drawText(text, x, y, paint)

            // Optionally add video title
            if (!videoTitle.isNullOrEmpty()) {
                val titlePaint = Paint().apply {
                    color = Color.WHITE
                    textSize = 18f
                    isAntiAlias = true
                    setShadowLayer(2f, 1f, 1f, Color.BLACK)
                }
                val maxLen = 30
                val title = if (videoTitle.length > maxLen) videoTitle.substring(0, maxLen) + "..." else videoTitle
                canvas.drawText(title, 20f, result.height - 20f, titlePaint)
            }

            Logger.debug { "ScreenshotTimestampPatch: added timestamp $timestamp" }
            return result
        } catch (e: Throwable) {
            Logger.error(e) { "ScreenshotTimestampPatch: failed to add timestamp" }
            return null
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
