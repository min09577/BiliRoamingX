package app.revanced.bilibili.patches

import android.app.Activity
import android.provider.Settings.System
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object BrightnessLockPatch {

    private var savedBrightness = -1f
    private var isLocked = false

    @JvmStatic
    fun lockBrightness(activity: Activity?) {
        if (!Settings.BrightnessLock.get()) return
        if (activity == null || isLocked) return

        try {
            // Save current brightness
            val currentBrightness = System.getFloat(activity.contentResolver, System.SCREEN_BRIGHTNESS) / 255f
            savedBrightness = currentBrightness
            isLocked = true

            // Apply brightness to window
            val params = activity.window.attributes
            params.screenBrightness = currentBrightness
            activity.window.attributes = params

            Logger.debug { "BrightnessLock: locked at $currentBrightness" }
        } catch (e: Throwable) {
            Logger.error(e) { "BrightnessLock: failed to lock" }
        }
    }

    @JvmStatic
    fun unlockBrightness(activity: Activity?) {
        if (!isLocked) return

        try {
            // Restore system brightness
            val params = activity?.window?.attributes ?: return
            params.screenBrightness = -1f
            activity.window.attributes = params
            isLocked = false
            savedBrightness = -1f

            Logger.debug { "BrightnessLock: unlocked" }
        } catch (e: Throwable) {
            Logger.error(e) { "BrightnessLock: failed to unlock" }
        }
    }

    @JvmStatic
    fun isLocked(): Boolean = isLocked

    @JvmStatic
    fun onVideoStart(activity: Activity?) {
        if (Settings.BrightnessLock.get()) {
            lockBrightness(activity)
        }
    }

    @JvmStatic
    fun onVideoStop(activity: Activity?) {
        if (isLocked) {
            unlockBrightness(activity)
        }
    }
}
