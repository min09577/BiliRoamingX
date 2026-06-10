package app.revanced.bilibili.patches

import android.app.AlertDialog
import android.app.Activity
import android.graphics.Bitmap
import android.view.ScaleGestureDetector
import android.widget.ImageView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object CommentImagePreviewPatch {

    @JvmStatic
    fun showPreview(activity: Activity?, bitmap: Bitmap?) {
        if (!Settings.CommentImagePreview.get()) return
        if (activity == null || bitmap == null) return

        try {
            val imageView = ImageView(activity).apply {
                setImageBitmap(bitmap)
                scaleType = ImageView.ScaleType.FIT_CENTER
                setPadding(16, 16, 16, 16)
            }

            // Add pinch-to-zoom support
            var scaleFactor = 1f
            val scaleDetector = ScaleGestureDetector(activity, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
                override fun onScale(detector: ScaleGestureDetector): Boolean {
                    scaleFactor *= detector.scaleFactor
                    scaleFactor = scaleFactor.coerceIn(0.5f, 5f)
                    imageView.scaleX = scaleFactor
                    imageView.scaleY = scaleFactor
                    return true
                }
            })

            imageView.setOnTouchListener { _, event ->
                scaleDetector.onTouchEvent(event)
                true
            }

            AlertDialog.Builder(activity)
                .setTitle("评论图片预览")
                .setView(imageView)
                .setPositiveButton("关闭", null)
                .setNeutralButton("保存") { _, _ ->
                    // Save image
                    Logger.debug { "CommentImagePreview: save requested" }
                }
                .show()

            Logger.debug { "CommentImagePreview: showed preview" }
        } catch (e: Throwable) {
            Logger.error(e) { "CommentImagePreview: failed to show" }
        }
    }

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.CommentImagePreview.get()
    }
}
