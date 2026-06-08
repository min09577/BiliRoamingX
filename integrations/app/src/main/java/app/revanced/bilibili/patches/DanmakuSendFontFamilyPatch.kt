package app.revanced.bilibili.patches
import android.graphics.Typeface
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuSendFontFamilyPatch {
    const val FONT_DEFAULT = 0; const val FONT_SERIF = 1; const val FONT_MONO = 2
    @JvmStatic fun getFont(): Int = Settings.DanmakuSendFontFamily.get().coerceIn(0, 2)
    @JvmStatic fun getTypeface(): Typeface = when (getFont()) { FONT_SERIF -> Typeface.SERIF; FONT_MONO -> Typeface.MONOSPACE; else -> Typeface.DEFAULT }
}
