package app.revanced.bilibili.patches
import android.graphics.Paint
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuShadowStylePatch {
    const val SHADOW_DEFAULT = 0; const val SHADOW_NONE = 1; const val SHADOW_STRONG = 2; const val SHADOW_GLOW = 3
    @JvmStatic fun getStyle(): Int = Settings.DanmakuShadowStyle.get().coerceIn(0, 3)
    @JvmStatic fun applyShadow(paint: Paint) {
        when (getStyle()) {
            SHADOW_NONE -> paint.clearShadowLayer()
            SHADOW_STRONG -> paint.setShadowLayer(4f, 2f, 2f, -0x1000000)
            SHADOW_GLOW -> paint.setShadowLayer(6f, 0f, 0f, -0x1)
            else -> paint.setShadowLayer(2f, 1f, 1f, -0x1000000)
        }
    }
}
