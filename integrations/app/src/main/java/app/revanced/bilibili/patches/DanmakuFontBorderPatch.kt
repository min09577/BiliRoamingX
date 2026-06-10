package app.revanced.bilibili.patches

import android.graphics.Color
import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object DanmakuFontBorderPatch {

    @JvmStatic
    fun applyBorder(textView: TextView?) {
        if (!Settings.DanmakuFontBorder.get()) return
        if (textView == null) return

        try {
            textView.setShadowLayer(
                3f,  // radius
                1f,  // dx
                1f,  // dy
                Color.BLACK  // color
            )
            Logger.debug { "DanmakuFontBorder: applied border" }
        } catch (e: Throwable) {
            Logger.error(e) { "DanmakuFontBorder: failed to apply" }
        }
    }

    @JvmStatic
    fun getBorderColor(): Int {
        return Color.BLACK
    }

    @JvmStatic
    fun getBorderWidth(): Float {
        return 3f
    }
}
