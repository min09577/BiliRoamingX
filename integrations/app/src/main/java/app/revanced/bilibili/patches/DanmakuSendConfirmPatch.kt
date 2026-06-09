package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object DanmakuSendConfirmPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuSendConfirm.get()
    
    @JvmStatic fun shouldConfirm(): Boolean = isEnabled()
}
