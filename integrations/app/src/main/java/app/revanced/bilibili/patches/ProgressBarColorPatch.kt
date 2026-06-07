package app.revanced.bilibili.patches

import android.graphics.Color
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object ProgressBarColorPatch {

    @JvmStatic
    fun getProgressColor(): Int {
        val color = Settings.ProgressBarColor.get()
        return if (color != 0) color else Color.parseColor("#FB7299") // Default Bilibili pink
    }

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.ProgressBarColor.get() != 0
    }

    @JvmStatic
    fun applyColor(view: android.view.View?) {
        if (view == null) return
        try {
            val color = getProgressColor()
            // Try to apply to ProgressBar
            if (view is android.widget.ProgressBar) {
                view.progressTintList = android.content.res.ColorStateList.valueOf(color)
            }
            Logger.debug { "ProgressBarColor: applied color ${String.format("#%06X", 0xFFFFFF and color)}" }
        } catch (e: Throwable) {
            Logger.error(e) { "ProgressBarColor: failed to apply" }
        }
    }
}
