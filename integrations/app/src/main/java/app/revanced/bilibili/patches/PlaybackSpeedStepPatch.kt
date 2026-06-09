package app.revanced.bilibili.patches

import androidx.annotation.Keep
import java.util.concurrent.CopyOnWriteArrayList
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 播放速度步长自定义 — 自定义每次调节播放速度的步长
 * 默认步长 0.25x，可自定义为 0.1x/0.2x/0.5x 等
 */
@Keep
object PlaybackSpeedStepPatch {
    private const val TAG = "PlaybackSpeedStep"
    // Supported speed presets for bilibili player
    private val DEFAULT_SPEEDS = floatArrayOf(0.5f, 0.75f, 1.0f, 1.25f, 1.5f, 2.0f, 3.0f)
    // Min/Max bounds for custom step
    private const val MIN_STEP = 0.05f
    private const val MAX_STEP = 1.0f

    /**
     * Get the custom speed step value.
     */
    @JvmStatic
    fun getSpeedStep(): Float {
        val step = Settings.PlaybackSpeedStep.get()
        return if (step > 0f) step.coerceIn(MIN_STEP, MAX_STEP) else 0.25f
    }

    /**
     * Generate custom speed presets based on the step value.
     * Returns array of speeds from 0.25x to 5.0x with the configured step.
     */
    @JvmStatic
    fun getCustomSpeeds(): FloatArray {
        val step = getSpeedStep()
        val speeds = CopyOnWriteArrayList<Float>()
        var speed = 0.25f
        while (speed <= 5.0f) {
            // Round to avoid floating point issues
            val rounded = (Math.round(speed * 100f) / 100f)
            speeds.add(rounded)
            speed += step
        }
        Logger.debug { "$TAG: custom speeds: ${speeds.joinToString()}" }
        return speeds.toFloatArray()
    }

    /**
     * Get next speed up from current speed.
     * Used when user presses speed up button.
     */
    @JvmStatic
    fun getNextSpeedUp(currentSpeed: Float): Float {
        val step = getSpeedStep()
        val next = currentSpeed + step
        return if (next > 5.0f) 5.0f else (Math.round(next * 100f) / 100f)
    }

    /**
     * Get next speed down from current speed.
     * Used when user presses speed down button.
     */
    @JvmStatic
    fun getNextSpeedDown(currentSpeed: Float): Float {
        val step = getSpeedStep()
        val next = currentSpeed - step
        return if (next < 0.25f) 0.25f else (Math.round(next * 100f) / 100f)
    }

    /**
     * Hook target: replace the default speed list with custom one.
     * Called when building speed selector UI.
     */
    @JvmStatic
    fun getSpeedOptions(): Array<String> {
        val speeds = getCustomSpeeds()
        return Array(speeds.size) { i ->
            val s = speeds[i]
            if (s == s.toLong().toFloat()) "${s.toLong()}x" else "${s}x"
        }
    }
}
