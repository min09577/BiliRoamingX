package app.revanced.bilibili.patches

import android.graphics.*
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object VideoWatermarkPatch {

    @JvmStatic
    fun addWatermark(bitmap: Bitmap, text: String?): Bitmap? {
        if (!Settings.VideoWatermark.get()) return null
        if (text.isNullOrEmpty()) return null

        try {
            val result = bitmap.copy(Bitmap.Config.ARGB_8888, true) ?: return null
            val canvas = Canvas(result)

            val paint = Paint().apply {
                color = Color.argb(128, 255, 255, 255)
                textSize = 20f
                isAntiAlias = true
                typeface = Typeface.DEFAULT_BOLD
            }

            // Draw watermark in bottom-left
            canvas.drawText(text, 20f, result.height - 20f, paint)

            Logger.debug { "VideoWatermark: added watermark '$text'" }
            return result
        } catch (e: Throwable) {
            Logger.error(e) { "VideoWatermark: failed to add" }
            return null
        }
    }

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.VideoWatermark.get()
    }
}
