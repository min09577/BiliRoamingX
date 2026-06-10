package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerGestureVolumeStepPatch {
    /**
     * getStep
     */
    @JvmStatic fun getStep(): Int = Settings.PlayerGestureVolumeStep.get().coerceIn(0, 5)
    /**
     * getStepPercent
     */
    @JvmStatic fun getStepPercent(): Int = if (getStep() == 0) 5 else getStep()
}
