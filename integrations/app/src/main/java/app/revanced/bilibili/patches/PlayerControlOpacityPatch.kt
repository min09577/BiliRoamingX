package app.revanced.bilibili.patches
import android.view.View
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerControlOpacityPatch {
    /**
     * getOpacity
     */
    @JvmStatic fun getOpacity(): Int = Settings.PlayerControlOpacity.get().coerceIn(0, 100)
    /**
     * applyOpacity
     */
    @JvmStatic fun applyOpacity(view: View?) { view?.alpha = getOpacity() / 100f }
}
