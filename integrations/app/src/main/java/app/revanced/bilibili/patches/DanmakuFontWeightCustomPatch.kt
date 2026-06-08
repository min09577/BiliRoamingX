package app.revanced.bilibili.patches
import android.graphics.Paint
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuFontWeightCustomPatch {
    @JvmStatic fun getWeight(): Int = Settings.DanmakuFontWeightCustom.get().coerceIn(0, 3)
    @JvmStatic fun applyWeight(paint: Paint) {
        when (getWeight()) { 1 -> paint.strokeWidth = 0.5f; 2 -> paint.isFakeBoldText = true; 3 -> { paint.isFakeBoldText = true; paint.strokeWidth = 1.5f } }
    }
}
