package app.revanced.bilibili.patches

import android.graphics.Color
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object DanmakuSendColorPatch {

    @JvmStatic
    fun getSendColor(): Int {
        val color = Settings.DanmakuSendColor.get()
        return if (color != 0) color else Color.WHITE
    }

    @JvmStatic
    fun getSendColorHex(): String {
        return String.format("#%06X", 0xFFFFFF and getSendColor())
    }

    @JvmStatic
    fun applySendColor(textView: android.widget.TextView?) {
        if (textView == null) return
        try {
            textView.setTextColor(getSendColor())
            Logger.debug { "DanmakuSendColor: applied color ${getSendColorHex()}" }
        } catch (e: Throwable) {
            Logger.error(e) { "DanmakuSendColor: failed to apply" }
        }
    }
}
