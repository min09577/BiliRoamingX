package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object DanmakuFontStretchPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuFontStretch.get() != 1.0f
    /**
     * getStretch
     */
    @JvmStatic fun getStretch(): Float = Settings.DanmakuFontStretch.get()
}
