package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoAutoSkipTransitionPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoAutoSkipTransition.get()
    /**
     * shouldSkipTransition
     */
    @JvmStatic fun shouldSkipTransition(): Boolean = isEnabled()
}
