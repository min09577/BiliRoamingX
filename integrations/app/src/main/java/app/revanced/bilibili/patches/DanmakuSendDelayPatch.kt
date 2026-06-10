package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object DanmakuSendDelayPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuSendDelay.get() > 0
    /**
     * getDelayMs
     */
    @JvmStatic fun getDelayMs(): Int = Settings.DanmakuSendDelay.get()
}
