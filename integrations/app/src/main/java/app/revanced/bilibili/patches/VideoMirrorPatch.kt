package app.revanced.bilibili.patches

import android.view.SurfaceView
import android.view.TextureView
import android.view.View
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object VideoMirrorPatch {

    private var isMirrored = false

    @JvmStatic
    fun toggleMirror(player: Any?): Boolean {
        if (!Settings.VideoMirror.get()) return false

        try {
            isMirrored = !isMirrored
            applyMirror(player)
            Logger.debug { "VideoMirror: mirrored=$isMirrored" }
            return true
        } catch (e: Throwable) {
            Logger.error(e) { "VideoMirror: failed to toggle" }
            return false
        }
    }

    @JvmStatic
    fun applyMirror(player: Any?) {
        try {
            // Find the video view (SurfaceView or TextureView)
            val videoView = findVideoView(player) ?: return

            if (isMirrored) {
                videoView.scaleX = -1f
            } else {
                videoView.scaleX = 1f
            }
        } catch (e: Throwable) {
            Logger.error(e) { "VideoMirror: failed to apply" }
        }
    }

    private fun findVideoView(player: Any?): View? {
        if (player == null) return null
        try {
            // Try to find SurfaceView or TextureView through reflection
            val fields = player.javaClass.declaredFields
            for (field in fields) {
                field.isAccessible = true
                val value = field.get(player)
                if (value is SurfaceView || value is TextureView) {
                    return value
                }
                // Check nested objects
                if (value != null) {
                    val nestedFields = value.javaClass.declaredFields
                    for (nf in nestedFields) {
                        nf.isAccessible = true
                        val nv = nf.get(value)
                        if (nv is SurfaceView || nv is TextureView) {
                            return nv
                        }
                    }
                }
            }
        } catch (e: Throwable) {
            Logger.error(e) { "VideoMirror: failed to find video view" }
        }
        return null
    }

    @JvmStatic
    fun isMirrored(): Boolean = isMirrored

    @JvmStatic
    fun resetMirror() {
        isMirrored = false
    }
}
