package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object DanmakuPositionCustomPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuPositionCustom.get() != 0
    @JvmStatic fun getPosition(): Int = Settings.DanmakuPositionCustom.get()
}
