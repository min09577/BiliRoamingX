package app.revanced.bilibili.patches

import android.view.MotionEvent
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 手势调节音量 — 在播放器右侧上下滑动调节音量
 * 与亮度手势配合使用，提供更完整的手势控制
 */
@Keep
object PlayerGestureVolumePatch {
    private const val TAG = "GestureVolume"
    // Sensitivity: how many pixels = 1 volume step
    private const val SENSITIVITY = 15f
    // Volume range
    private const val MIN_VOLUME = 0f
    private const val MAX_VOLUME = 100f

    private var startY = 0f
    private var startVolume = 0
    private var isAdjusting = false
    private var volumeChanged = false

    /**
     * Check if gesture volume control is enabled.
     */
    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.PlayerGestureVolume.get()
    }

    /**
     * Hook target: handle touch event for volume gesture.
     * Should be called on the player's right side touch area.
     * @param event the touch event
     * @param currentVolume current system volume (0-100)
     * @return new volume if changed, or -1 if not handled
     */
    @JvmStatic
    fun onTouchEvent(event: MotionEvent, currentVolume: Int): Int {
        if (!Settings.PlayerGestureVolume.get()) return -1

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startY = event.y
                startVolume = currentVolume
                isAdjusting = true
                volumeChanged = false
                return -1
            }
            MotionEvent.ACTION_MOVE -> {
                if (!isAdjusting) return -1

                val deltaY = startY - event.y
                val volumeDelta = (deltaY / SENSITIVITY).toInt()

                if (volumeDelta != 0) {
                    volumeChanged = true
                    val newVolume = (startVolume + volumeDelta).coerceIn(
                        MIN_VOLUME.toInt(), MAX_VOLUME.toInt()
                    )
                    Logger.debug { "$TAG: volume $currentVolume -> $newVolume" }
                    return newVolume
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
     * Check if gesture was active (for UI feedback).
     */
    @JvmStatic
    fun isAdjusting(): Boolean {
        return isAdjusting
    }

    /**
     * Check if volume was actually changed during gesture.
     */
    @JvmStatic
    fun wasVolumeChanged(): Boolean {
        return volumeChanged
    }
}
