package app.revanced.bilibili.patches

import android.view.MotionEvent
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 手势调节亮度 — 在播放器左侧上下滑动调节屏幕亮度
 * 与右侧音量手势配合，提供完整的手势控制体验
 */
@Keep
object PlayerGestureBrightnessPatch {
    private const val TAG = "GestureBrightness"
    // Sensitivity: how many pixels = 1% brightness
    private const val SENSITIVITY = 12f
    // Brightness range
    private const val MIN_BRIGHTNESS = 0
    private const val MAX_BRIGHTNESS = 255

    private var startY = 0f
    private var startBrightness = 0
    private var isAdjusting = false
    private var brightnessChanged = false

    /**
     * Check if gesture brightness control is enabled.
     */
    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.PlayerGestureBrightness.get()
    }

    /**
     * Hook target: handle touch event for brightness gesture.
     * Should be called on the player's left side touch area.
     * @param event the touch event
     * @param currentBrightness current screen brightness (0-255)
     * @return new brightness if changed, or -1 if not handled
     */
    @JvmStatic
    fun onTouchEvent(event: MotionEvent, currentBrightness: Int): Int {
        if (!Settings.PlayerGestureBrightness.get()) return -1

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startY = event.y
                startBrightness = currentBrightness
                isAdjusting = true
                brightnessChanged = false
                return -1
            }
            MotionEvent.ACTION_MOVE -> {
                if (!isAdjusting) return -1

                val deltaY = startY - event.y
                val brightnessDelta = (deltaY / SENSITIVITY).toInt()

                if (brightnessDelta != 0) {
                    brightnessChanged = true
                    val newBrightness = (startBrightness + brightnessDelta * 3).coerceIn(
                        MIN_BRIGHTNESS, MAX_BRIGHTNESS
                    )
                    Logger.debug { "$TAG: brightness $currentBrightness -> $newBrightness" }
                    return newBrightness
                }
                return -1
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                isAdjusting = false
                return -1
            }
        }
        return -1
    }

    /**
     * Check if gesture was active.
     */
    @JvmStatic
    fun isAdjusting(): Boolean {
        return isAdjusting
    }

    /**
     * Check if brightness was actually changed during gesture.
     */
    @JvmStatic
    fun wasBrightnessChanged(): Boolean {
        return brightnessChanged
    }
}
