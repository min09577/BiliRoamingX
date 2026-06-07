package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object DanmakuSendFontPatch {

    @JvmStatic
    fun getSendFontSize(): Int {
        val size = Settings.DanmakuSendFont.get()
        return if (size > 0) size else 25
    }

    @JvmStatic
    fun applySendFont(textView: android.widget.TextView?) {
        if (textView == null) return
        try {
            textView.textSize = getSendFontSize().toFloat()
            Logger.debug { "DanmakuSendFont: applied size ${getSendFontSize()}" }
        } catch (e: Throwable) {
            Logger.error(e) { "DanmakuSendFont: failed to apply" }
        }
    }
}
