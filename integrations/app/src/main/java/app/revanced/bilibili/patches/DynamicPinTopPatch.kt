package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object DynamicPinTopPatch {
    private const val TAG = "PinTop"
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicPinTop.get()
    @JvmStatic fun shouldPinTop(): Boolean {
        val pin = isEnabled()
        Logger.debug { "$TAG: pin top=$pin" }
        return pin
    }
}
