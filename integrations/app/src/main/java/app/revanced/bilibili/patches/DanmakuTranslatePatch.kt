package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object DanmakuTranslatePatch {
    private const val TAG = "DanmakuTranslate"
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuTranslate.get()
    @JvmStatic fun shouldTranslate(text: String): Boolean {
        if (!isEnabled()) return false
        return text.any { it.code > 0x7F } && !text.any { it.code in 0x4E00..0x9FFF }
    }
}
