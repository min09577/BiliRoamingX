package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object DanmakuSendCooldownPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuSendCooldown.get() > 0
    /**
     * getCooldownMs
     */
    @JvmStatic fun getCooldownMs(): Int = Settings.DanmakuSendCooldown.get()
}
