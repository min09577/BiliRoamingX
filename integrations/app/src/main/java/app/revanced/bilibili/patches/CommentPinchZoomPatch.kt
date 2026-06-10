package app.revanced.bilibili.patches

import android.annotation.SuppressLint
import android.view.ScaleGestureDetector
import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object CommentPinchZoomPatch {

    private var scaleFactor = 1.0f
    private var baseTextSize = 14f
    private var scaleDetector: ScaleGestureDetector? = null

    @JvmStatic
    fun onScaleBegin(detector: ScaleGestureDetector) {
        if (!Settings.CommentPinchZoom.get()) return
        scaleFactor = 1.0f
        Logger.debug { "CommentPinchZoomPatch: scale begin" }
    }

    @JvmStatic
    fun onScale(detector: ScaleGestureDetector) {
        if (!Settings.CommentPinchZoom.get()) return
        scaleFactor *= detector.scaleFactor
        scaleFactor = scaleFactor.coerceIn(0.5f, 3.0f) // Limit 50% to 300%
    }

    @JvmStatic
    @SuppressLint("SetTextI18n")
    fun onScaleEnd(detector: ScaleGestureDetector, commentView: TextView?) {
        if (!Settings.CommentPinchZoom.get()) return
        if (commentView == null) return

        try {
            val newSize = baseTextSize * scaleFactor
            commentView.textSize = newSize
            Logger.debug { "CommentPinchZoomPatch: text size changed to $newSize (scale: $scaleFactor)" }
        } catch (e: Throwable) {
            Logger.error(e) { "CommentPinchZoomPatch: failed to change text size" }
        }
    }

    @JvmStatic
    fun initScaleDetector(activity: android.app.Activity, commentView: TextView): ScaleGestureDetector {
        baseTextSize = commentView.textSize
        return ScaleGestureDetector(activity, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
                this@CommentPinchZoomPatch.onScaleBegin(detector)
                return true
            }

            override fun onScale(detector: ScaleGestureDetector): Boolean {
                this@CommentPinchZoomPatch.onScale(detector)
                return true
            }

            override fun onScaleEnd(detector: ScaleGestureDetector) {
                this@CommentPinchZoomPatch.onScaleEnd(detector, commentView)
            }
        })
    }
}
