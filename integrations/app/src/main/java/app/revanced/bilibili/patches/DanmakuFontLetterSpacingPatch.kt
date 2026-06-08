package app.revanced.bilibili.patches
import android.graphics.Paint
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuFontLetterSpacingPatch {
    @JvmStatic fun getSpacing(): Float = Settings.DanmakuFontLetterSpacing.get().coerceIn(-0.1f, 0.5f)
    @JvmStatic fun applySpacing(paint: Paint) { paint.letterSpacing = getSpacing() }
}
