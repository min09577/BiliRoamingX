package app.revanced.bilibili.patches

import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object SubtitlePositionPatch {

    const val POSITION_BOTTOM = 0
    const val POSITION_CENTER = 1
    const val POSITION_TOP = 2

    @JvmStatic
    fun applyPosition(textView: TextView?, parentHeight: Int) {
        if (textView == null || parentHeight <= 0) return

        try {
            val position = Settings.SubtitlePosition.get()
            when (position) {
                POSITION_TOP -> {
                    textView.y = 20f
                }
                POSITION_CENTER -> {
                    textView.y = parentHeight / 2f - textView.height / 2f
                }
                POSITION_BOTTOM -> {
                    textView.y = parentHeight - textView.height - 20f
                }
            }
            Logger.debug { "SubtitlePosition: applied position $position" }
        } catch (e: Throwable) {
            Logger.error(e) { "SubtitlePosition: failed to apply" }
        }
    }

    @JvmStatic
    fun getPosition(): Int {
        return Settings.SubtitlePosition.get()
    }
}
