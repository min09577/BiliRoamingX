package app.revanced.bilibili.patches

import android.app.Activity
import android.view.MotionEvent
import android.view.WindowManager
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.Toasts

@Keep
object TouchLockPatch {

    private var isLocked = false
    private var lastTapTime = 0L
    private var tapCount = 0
    private const val DOUBLE_TAP_MS = 300L

    @JvmStatic
    fun onTouchEvent(activity: Activity?, event: MotionEvent?): Boolean {
        if (!Settings.TouchLock.get()) return false
        if (activity == null || event == null) return false
        if (!isLocked) return false

        // Only allow double-tap to unlock
        if (event.action == MotionEvent.ACTION_DOWN) {
            val now = System.currentTimeMillis()
            if (now - lastTapTime < DOUBLE_TAP_MS) {
                tapCount++
                if (tapCount >= 2) {
                    unlock(activity)
                    return false
                }
            } else {
                tapCount = 1
            }
            lastTapTime = now
        }

        // Consume all touch events when locked
        return true
    }

    @JvmStatic
    fun toggleLock(activity: Activity?) {
        if (!Settings.TouchLock.get()) return
        if (activity == null) return

        if (isLocked) {
            unlock(activity)
        } else {
            lock(activity)
        }
    }

    private fun lock(activity: Activity) {
        try {
            isLocked = true
            // Keep screen on while locked
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            Toasts.showShort("🔒 屏幕已锁定，双击解锁")
            Logger.debug { "TouchLock: locked" }
        } catch (e: Throwable) {
            Logger.error(e) { "TouchLock: failed to lock" }
        }
    }

    private fun unlock(activity: Activity) {
        try {
            isLocked = false
            tapCount = 0
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            Toasts.showShort("🔓 屏幕已解锁")
            Logger.debug { "TouchLock: unlocked" }
        } catch (e: Throwable) {
            Logger.error(e) { "TouchLock: failed to unlock" }
        }
    }

    @JvmStatic
    fun isLocked(): Boolean = isLocked

    @JvmStatic
    fun resetLock() {
        isLocked = false
        tapCount = 0
    }
}
