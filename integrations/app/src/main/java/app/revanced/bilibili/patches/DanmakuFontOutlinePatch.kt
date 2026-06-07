package app.revanced.bilibili.patches

import android.graphics.Color
import android.graphics.Paint
import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object DanmakuFontOutlinePatch {

    @JvmStatic
    fun applyOutline(textView: TextView?) {
        if (!Settings.DanmakuFontOutline.get()) return
        if (textView == null) return

        try {
            val paint = textView.paint
            paint.style = Paint.Style.FILL_AND_STROKE
            paint.strokeWidth = 1.5f
            paint.strokeJoin = Paint.Join.ROUND
            textView.setTextColor(Color.WHITE)
            textView.setShadowLayer(0f, 0f, 0f, Color.TRANSPARENT)
            Logger.debug { "DanmakuFontOutline: applied outline" }
        } catch (e: Throwable) {
            Logger.error(e) { "DanmakuFontOutline: failed to apply" }
        }
    }

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.DanmakuFontOutline.get()
    }
}
