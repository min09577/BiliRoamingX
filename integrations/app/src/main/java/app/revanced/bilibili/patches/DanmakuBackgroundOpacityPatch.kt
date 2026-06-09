package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object DanmakuBackgroundOpacityPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuBackgroundOpacity.get() > 0
    @JvmStatic fun getOpacity(): Int = Settings.DanmakuBackgroundOpacity.get()
}
