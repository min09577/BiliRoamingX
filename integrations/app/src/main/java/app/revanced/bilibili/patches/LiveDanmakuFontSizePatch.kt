package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object LiveDanmakuFontSizePatch {
    @JvmStatic fun getFontSize(): Int = Settings.LiveDanmakuFontSize.get().coerceIn(0, 3)
    @JvmStatic fun getScale(): Float = when (getFontSize()) { 1 -> 0.8f; 2 -> 1.2f; 3 -> 1.5f; else -> 1.0f }
}
