package app.revanced.bilibili.patches

import android.view.MotionEvent
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 手势快进快退 — 在播放器左右滑动快进/快退
 * 左滑后退，右滑前进，每次步进5秒
 */
@Keep
object PlayerGestureSeekPatch {
    private const val TAG = "GestureSeek"
    private const val SEEK_STEP_MS = 5000L

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.PlayerGestureSeek.get()
    }

    @JvmStatic
    fun getSeekStepMs(): Long {
        return SEEK_STEP_MS
    }

    @JvmStatic
    fun calculateSeekDelta(distanceX: Float, screenWidth: Int): Long {
        if (!isEnabled()) return 0L
        val ratio = (-distanceX / screenWidth).coerceIn(-1f, 1f)
        val seekMs = (ratio * 30000).toLong() // max 30s per swipe
        Logger.debug { "$TAG: seek delta=${seekMs}ms" }
        return seekMs
    }

    @JvmStatic
    fun shouldHandleGesture(event: MotionEvent): Boolean {
        return isEnabled() && event.pointerCount == 1
    }
}