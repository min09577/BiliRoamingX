package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerGestureVolumeStepPatch {
    @JvmStatic fun getStep(): Int = Settings.PlayerGestureVolumeStep.get().coerceIn(0, 5)
    @JvmStatic fun getStepPercent(): Int = if (getStep() == 0) 5 else getStep()
}
