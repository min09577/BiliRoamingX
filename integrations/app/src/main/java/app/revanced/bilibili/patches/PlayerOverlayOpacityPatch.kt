package app.revanced.bilibili.patches

import android.view.View
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object PlayerOverlayOpacityPatch {

    @JvmStatic
    fun applyOpacity(view: View?) {
        if (view == null) return

        try {
            val opacity = Settings.PlayerOverlayOpacity.get()
            if (opacity in 0..100) {
                view.alpha = opacity / 100f
                Logger.debug { "OverlayOpacity: applied $opacity%" }
            }
        } catch (e: Throwable) {
            Logger.error(e) { "OverlayOpacity: failed to apply" }
        }
    }

    @JvmStatic
    fun getOpacity(): Float {
        return Settings.PlayerOverlayOpacity.get() / 100f
    }

    @JvmStatic
    fun getOpacityPercent(): Int {
        return Settings.PlayerOverlayOpacity.get()
    }
}
