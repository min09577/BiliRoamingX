package app.revanced.bilibili.patches

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object VideoRotationLockPatch {

    private var isLocked = false
    private var lockedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

    @JvmStatic
    fun lockRotation(activity: Activity?, orientation: Int) {
        if (!Settings.VideoRotationLock.get()) return
        if (activity == null) return

        try {
            isLocked = true
            lockedOrientation = orientation
            activity.requestedOrientation = orientation
            Logger.debug { "RotationLock: locked to $orientation" }
        } catch (e: Throwable) {
            Logger.error(e) { "RotationLock: failed to lock" }
        }
    }

    @JvmStatic
    fun unlockRotation(activity: Activity?) {
        if (!isLocked) return
        if (activity == null) return

        try {
            isLocked = false
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            Logger.debug { "RotationLock: unlocked" }
        } catch (e: Throwable) {
            Logger.error(e) { "RotationLock: failed to unlock" }
        }
    }

    @JvmStatic
    fun isLocked(): Boolean = isLocked

    @JvmStatic
    fun getLockedOrientation(): Int = lockedOrientation

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.VideoRotationLock.get()
    }
}
