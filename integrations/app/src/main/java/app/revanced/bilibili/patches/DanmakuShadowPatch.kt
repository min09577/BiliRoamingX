package app.revanced.bilibili.patches

import android.graphics.Color
import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object DanmakuShadowPatch {

    @JvmStatic
    fun applyShadow(textView: TextView?) {
        if (!Settings.DanmakuShadow.get()) return
        if (textView == null) return

        try {
            textView.setShadowLayer(
                4f,  // radius
                2f,  // dx
                2f,  // dy
                Color.BLACK  // color
            )
            Logger.debug { "DanmakuShadow: applied shadow" }
        } catch (e: Throwable) {
            Logger.error(e) { "DanmakuShadow: failed to apply" }
        }
    }

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.DanmakuShadow.get()
    }
}
