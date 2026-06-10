package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object DanmakuFontLineHeightPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuFontLineHeight.get() != 1.0f
    /**
     * getLineHeight
     */
    @JvmStatic fun getLineHeight(): Float = Settings.DanmakuFontLineHeight.get()
}
