package app.revanced.bilibili.patches

import android.graphics.Bitmap
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import java.io.ByteArrayOutputStream

/**
 * 截图格式设置 — 自定义播放器截图的保存格式
 * 支持 PNG（无损）、JPG（有损，体积小）、WebP（高效压缩）
 */
@Keep
object PlayerScreenshotFormatPatch {
    private const val TAG = "ScreenshotFormat"
    // Format constants matching setting values
    const val FORMAT_PNG = 0
    const val FORMAT_JPG = 1
    const val FORMAT_WEBP = 2

    // Default JPEG quality
    private const val DEFAULT_JPEG_QUALITY = 95
    private const val DEFAULT_WEBP_QUALITY = 90

    /**
     * Get the configured screenshot format.
     * @return format constant (FORMAT_PNG, FORMAT_JPG, FORMAT_WEBP)
     */
    @JvmStatic
    fun getFormat(): Int {
        return Settings.PlayerScreenshotFormat.get().coerceIn(0, 2)
    }

    /**
     * Get the MIME type for the configured format.
     */
    @JvmStatic
    fun getMimeType(): String {
        return when (getFormat()) {
            FORMAT_JPG -> "image/jpeg"
            FORMAT_WEBP -> "image/webp"
            else -> "image/png"
        }
    }

    /**
     * Get the file extension for the configured format.
     */
    @JvmStatic
    fun getFileExtension(): String {
        return when (getFormat()) {
            FORMAT_JPG -> ".jpg"
            FORMAT_WEBP -> ".webp"
            else -> ".png"
        }
    }

    /**
     * Hook target: compress bitmap to the configured format.
     * Called when saving screenshot.
     * @param bitmap the screenshot bitmap
     * @return compressed byte array
     */
    @JvmStatic
    fun compressBitmap(bitmap: Bitmap): ByteArray {
        val format = getFormat()
        val stream = ByteArrayOutputStream()

        val compressFormat = when (format) {
            FORMAT_JPG -> Bitmap.CompressFormat.JPEG
            FORMAT_WEBP -> Bitmap.CompressFormat.WEBP
            else -> Bitmap.CompressFormat.PNG
        }

        val quality = when (format) {
            FORMAT_JPG -> DEFAULT_JPEG_QUALITY
            FORMAT_WEBP -> DEFAULT_WEBP_QUALITY
            else -> 100 // PNG is lossless, quality is ignored
        }

        bitmap.compress(compressFormat, quality, stream)
        val bytes = stream.toByteArray()
        Logger.debug { "$TAG: compressed screenshot as ${getFileExtension()}, size=${bytes.size}" }
        return bytes
    }

    /**
     * Hook target: get quality hint for the format.
     * Used for UI display or optimization hints.
     */
    @JvmStatic
    fun getQualityHint(): String {
        return when (getFormat()) {
            FORMAT_JPG -> "JPG ($DEFAULT_JPEG_QUALITY%)"
            FORMAT_WEBP -> "WebP ($DEFAULT_WEBP_QUALITY%)"
            else -> "PNG (无损)"
        }
    }
}
