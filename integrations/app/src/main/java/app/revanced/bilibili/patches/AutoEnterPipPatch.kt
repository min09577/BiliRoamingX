package app.revanced.bilibili.patches

import android.app.Activity
import android.content.res.Configuration
import android.view.KeyEvent
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object AutoEnterPipPatch {

    private var lastActivity: Activity? = null

    @JvmStatic
    fun onActivityResumed(activity: Activity) {
        if (!Settings.AutoEnterPip.get()) return
        lastActivity = activity
    }

    @JvmStatic
    fun onActivityPaused(activity: Activity) {
        if (!Settings.AutoEnterPip.get()) return
        if (activity != lastActivity) return

        // Check if we should enter PiP
        try {
            // Only enter PiP if not already in PiP and video is playing
            if (activity.isInPictureInPictureMode) return

            // Try to enter PiP
            val enterPipMethod = Activity::class.java.getDeclaredMethod("enterPictureInPictureMode", android.app.PictureInPictureParams::class.java)
            enterPipMethod.isAccessible = true

            val paramsBuilder = Class.forName("android.app.PictureInPictureParams\$Builder").getConstructor(Activity::class.java)
            val params = paramsBuilder.newInstance(activity)
            val buildMethod = paramsBuilder.newInstance(activity).javaClass.getMethod("build")
            val pipParams = buildMethod.invoke(params)

            enterPipMethod.invoke(activity, pipParams)
            Logger.debug { "AutoEnterPipPatch: entered PiP mode" }
        } catch (e: Throwable) {
            // PiP not supported or failed
            Logger.error(e) { "AutoEnterPipPatch: failed to enter PiP" }
        }
    }

    @JvmStatic
    fun onKeyDown(activity: Activity?, keyCode: Int, event: KeyEvent?): Boolean {
        if (!Settings.AutoEnterPip.get()) return false
        if (keyCode != KeyEvent.KEYCODE_HOME) return false

        // Let the system handle Home key, our onActivityPaused will trigger PiP
        return false
    }

    @JvmStatic
    fun setPipAspectRatio(activity: Activity?) {
        if (activity == null) return
        if (activity.isInPictureInPictureMode) {
            try {
                val aspectRatio = Settings.PipAspectRatio.get()
                // Set PiP aspect ratio if supported (Android 12+)
                if (android.os.Build.VERSION.SDK_INT >= 31) {
                    val paramsMethod = Activity::class.java.getMethod("setPictureInPictureParams", android.app.PictureInPictureParams::class.java)
                    val paramsBuilder = Class.forName("android.app.PictureInPictureParams\$Builder").getConstructor()
                    val builder = paramsBuilder.newInstance()
                    val setAspectRatioMethod = builder.javaClass.getMethod("setAspectRatio", Float::class.java)
                    when (aspectRatio) {
                        1 -> setAspectRatioMethod.invoke(builder, 16f / 9f)
                        2 -> setAspectRatioMethod.invoke(builder, 4f / 3f)
                        3 -> setAspectRatioMethod.invoke(builder, 1f)
                        else -> return
                    }
                    val buildMethod = builder.javaClass.getMethod("build")
                    val pipParams = buildMethod.invoke(builder)
                    paramsMethod.invoke(activity, pipParams)
                    Logger.debug { "AutoEnterPipPatch: set PiP aspect ratio to $aspectRatio" }
                }
            } catch (e: Throwable) {
                Logger.error(e) { "AutoEnterPipPatch: failed to set PiP aspect ratio" }
            }
        }
    }
}
