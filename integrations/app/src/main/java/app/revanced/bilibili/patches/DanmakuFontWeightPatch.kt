package app.revanced.bilibili.patches
import android.graphics.Paint
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuFontWeightPatch {
    const val WEIGHT_DEFAULT = 0; const val WEIGHT_LIGHT = 1; const val WEIGHT_BOLD = 2; const val WEIGHT_EXTRA_BOLD = 3
    /**
     * getWeight
     */
    @JvmStatic fun getWeight(): Int = Settings.DanmakuFontWeight.get().coerceIn(0, 3)
    /**
     * applyWeight
     */
    @JvmStatic fun applyWeight(paint: Paint) {
        when (getWeight()) {
            WEIGHT_LIGHT -> paint.strokeWidth = 0.5f
            WEIGHT_BOLD -> paint.isFakeBoldText = true
            WEIGHT_EXTRA_BOLD -> { paint.isFakeBoldText = true; paint.strokeWidth = 1.5f }
        }
    }
}
