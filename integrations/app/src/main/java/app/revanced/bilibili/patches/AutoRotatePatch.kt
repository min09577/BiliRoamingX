package app.revanced.bilibili.patches

import android.app.Activity
import android.content.pm.ActivityInfo
import android.provider.Settings.System
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object AutoRotatePatch {

    private var savedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    private var autoRotationEnabled = false

    @JvmStatic
    fun onEnterFullScreen(activity: Activity?) {
        if (!Settings.AutoRotate.get()) return
        if (activity == null) return

        try {
            // Save current orientation
            savedOrientation = activity.requestedOrientation

            // Check if auto-rotation is enabled in system settings
            val autoRotate = System.getInt(activity.contentResolver, System.ACCELEROMETER_ROTATION, 0)
            autoRotationEnabled = autoRotate == 1

            if (autoRotationEnabled) {
                // Force landscape
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                Logger.debug { "AutoRotate: forced landscape" }
            }
        } catch (e: Throwable) {
            Logger.error(e) { "AutoRotate: failed to enter fullscreen" }
        }
    }

    @JvmStatic
    fun onExitFullScreen(activity: Activity?) {
        if (!Settings.AutoRotate.get()) return
        if (activity == null) return

        try {
            // Restore original orientation
            activity.requestedOrientation = savedOrientation
            Logger.debug { "AutoRotate: restored orientation" }
        } catch (e: Throwable) {
            Logger.error(e) { "AutoRotate: failed to exit fullscreen" }
        }
    }

    @JvmStatic
    fun shouldAutoRotate(): Boolean {
        return Settings.AutoRotate.get()
    }
}
