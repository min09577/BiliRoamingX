package app.revanced.bilibili.patches

import android.graphics.Bitmap
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import java.io.ByteArrayOutputStream

/**
 * 截图质量设置 — 调节播放器截图的压缩质量
 * 1-100，数值越高质量越好，文件越大
 * 与PlayerScreenshotFormat配合使用
 */
@Keep
object PlayerScreenshotQualityPatch {
    private const val TAG = "ScreenshotQuality"
    private const val MIN_QUALITY = 10
    private const val MAX_QUALITY = 100

    @JvmStatic
    fun getQuality(): Int {
        return Settings.PlayerScreenshotQuality.get().coerceIn(MIN_QUALITY, MAX_QUALITY)
    }

    @JvmStatic
    fun applyQuality(bitmap: Bitmap, format: Int): ByteArray {
        val quality = getQuality()
        val stream = ByteArrayOutputStream()
        val compressFormat = when (format) {
            1 -> Bitmap.CompressFormat.JPEG
            2 -> Bitmap.CompressFormat.WEBP
            else -> Bitmap.CompressFormat.PNG
        }
        bitmap.compress(compressFormat, quality, stream)
        val bytes = stream.toByteArray()
        Logger.debug { "$TAG: compressed at quality=$quality, size=${bytes.size}" }
        return bytes
    }

    @JvmStatic
    fun getQualityDescription(): String {
        val q = getQuality()
        return when {
            q >= 90 -> "高质量 ($q%)"
            q >= 70 -> "中等质量 ($q%)"
            q >= 50 -> "较低质量 ($q%)"
            else -> "最低质量 ($q%)"
        }
    }
}
