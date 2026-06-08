package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuSendFontCustomPatch {
    const val FONT_DEFAULT = 0; const val FONT_BOLD = 1; const val FONT_ITALIC = 2
    @JvmStatic fun getFontPreset(): Int = Settings.DanmakuSendFontCustom.get().coerceIn(0, 2)
}
