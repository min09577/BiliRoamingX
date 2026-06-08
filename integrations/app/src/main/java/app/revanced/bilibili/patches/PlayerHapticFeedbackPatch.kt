package app.revanced.bilibili.patches
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerHapticFeedbackPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerHapticFeedback.get()
    @JvmStatic fun performHaptic(view: View?) {
        if (isEnabled()) view?.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
    }
}
