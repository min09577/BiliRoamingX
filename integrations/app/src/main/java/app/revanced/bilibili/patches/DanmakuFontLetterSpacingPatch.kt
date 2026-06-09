package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object DanmakuFontLetterSpacingPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuFontLetterSpacing.get() != 0f
    @JvmStatic fun getLetterSpacing(): Float = Settings.DanmakuFontLetterSpacing.get()
}
