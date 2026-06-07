package app.revanced.bilibili.patches

import android.app.Activity
import android.media.AudioManager
import android.view.MotionEvent
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import kotlin.math.abs

@Keep
object SwipeGestureCustomPatch {

    private var startX = 0f
    private var startY = 0f
    private var isSwiping = false
    private var isLeftSide = true

    @JvmStatic
    fun onTouchEvent(activity: Activity?, event: MotionEvent?): Boolean {
        if (!Settings.SwipeGestureCustom.get()) return false
        if (activity == null || event == null) return false

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
                isSwiping = false
                isLeftSide = startX < activity.window.decorView.width / 2
            }
            MotionEvent.ACTION_MOVE -> {
                if (!isSwiping) {
                    val dx = event.x - startX
                    val dy = event.y - startY
                    if (abs(dx) > 50 && abs(dx) > abs(dy)) {
                        isSwiping = true
                    }
                }
                if (isSwiping) {
                    val dy = startY - event.y
                    if (isLeftSide) {
                        adjustBrightness(activity, dy)
                    } else {
                        adjustVolume(activity, dy)
                    }
                    return true
                }
            }
            MotionEvent.ACTION_UP -> {
                isSwiping = false
            }
        }
        return false
    }

    private fun adjustBrightness(activity: Activity, delta: Float) {
        try {
            val params = activity.window.attributes
            val brightness = (params.screenBrightness + delta / 1000f).coerceIn(0.01f, 1f)
            params.screenBrightness = brightness
            activity.window.attributes = params
            Logger.debug { "SwipeGestureCustom: brightness=$brightness" }
        } catch (e: Throwable) {
            Logger.error(e) { "SwipeGestureCustom: adjust brightness failed" }
        }
    }

    private fun adjustVolume(activity: Activity, delta: Float) {
        try {
            val audio = activity.getSystemService(Activity.AUDIO_SERVICE) as AudioManager
            val max = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
            val cur = audio.getStreamVolume(AudioManager.STREAM_MUSIC)
            val newVol = (cur + (delta / 20f).toInt()).coerceIn(0, max)
            audio.setStreamVolume(AudioManager.STREAM_MUSIC, newVol, 0)
            Logger.debug { "SwipeGestureCustom: volume=$newVol" }
        } catch (e: Throwable) {
            Logger.error(e) { "SwipeGestureCustom: adjust volume failed" }
        }
    }
}
