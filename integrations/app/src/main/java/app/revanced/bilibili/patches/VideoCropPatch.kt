package app.revanced.bilibili.patches

import android.view.SurfaceView
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object VideoCropPatch {

    private var isCropped = false
    private var originalScaleX = 1f
    private var originalScaleY = 1f
    private var originalTranslationX = 0f
    private var originalTranslationY = 0f

    @JvmStatic
    fun toggleCrop(player: Any?): Boolean {
        if (!Settings.VideoCrop.get()) return false

        try {
            isCropped = !isCropped
            applyCrop(player)
            Logger.debug { "VideoCrop: cropped=$isCropped" }
            return true
        } catch (e: Throwable) {
            Logger.error(e) { "VideoCrop: failed to toggle" }
            return false
        }
    }

    @JvmStatic
    fun applyCrop(player: Any?) {
        try {
            val videoView = findVideoView(player) ?: return

            if (isCropped) {
                // Save original values
                originalScaleX = videoView.scaleX
                originalScaleY = videoView.scaleY
                originalTranslationX = videoView.translationX
                originalTranslationY = videoView.translationY

                // Apply crop - scale to fill
                val parent = videoView.parent as? ViewGroup ?: return
                val parentWidth = parent.width.toFloat()
                val parentHeight = parent.height.toFloat()
                val viewWidth = videoView.width.toFloat()
                val viewHeight = videoView.height.toFloat()

                if (viewWidth > 0 && viewHeight > 0) {
                    val scaleX = parentWidth / viewWidth
                    val scaleY = parentHeight / viewHeight
                    val scale = maxOf(scaleX, scaleY)

                    videoView.scaleX = scale
                    videoView.scaleY = scale
                }
            } else {
                // Restore original
                videoView.scaleX = originalScaleX
                videoView.scaleY = originalScaleY
                videoView.translationX = originalTranslationX
                videoView.translationY = originalTranslationY
            }
        } catch (e: Throwable) {
            Logger.error(e) { "VideoCrop: failed to apply" }
        }
    }

    private fun findVideoView(player: Any?): View? {
        if (player == null) return null
        try {
            val fields = player.javaClass.declaredFields
            for (field in fields) {
                field.isAccessible = true
                val value = field.get(player)
                if (value is SurfaceView || value is TextureView) {
                    return value
                }
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
        } catch (e: Exception) { e.printStackTrace() }
        return null
    }

    @JvmStatic
    fun isCropped(): Boolean = isCropped

    @JvmStatic
    fun resetCrop() {
        isCropped = false
    }
}
