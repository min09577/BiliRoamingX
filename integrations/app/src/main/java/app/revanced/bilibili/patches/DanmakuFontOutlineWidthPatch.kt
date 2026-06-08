package app.revanced.bilibili.patches
import android.graphics.Paint
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuFontOutlineWidthPatch {
    @JvmStatic fun getOutlineWidth(): Float = Settings.DanmakuFontOutlineWidth.get().coerceIn(0f, 5f)
    @JvmStatic fun applyOutline(paint: Paint) {
        val w = getOutlineWidth()
        if (w > 0f) paint.strokeWidth = w
    }
}
