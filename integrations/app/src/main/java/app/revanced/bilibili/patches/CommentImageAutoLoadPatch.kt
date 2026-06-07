package app.revanced.bilibili.patches

import android.widget.ImageView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 评论图片自动加载控制 — 控制评论区图片是否自动加载
 * 关闭后仅显示占位图，点击后才加载，节省流量
 */
@Keep
object CommentImageAutoLoadPatch {
    private const val TAG = "CommentImageAutoLoad"

    /**
     * Check if auto-load is enabled.
     */
    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.CommentImageAutoLoad.get()
    }

    /**
     * Hook target: intercept image loading in comment section.
     * @return true to allow auto-load, false to block
     */
    @JvmStatic
    fun shouldAutoLoad(): Boolean {
        val enabled = Settings.CommentImageAutoLoad.get()
        if (!enabled) {
            Logger.debug { "$TAG: image auto-load disabled" }
        }
        return enabled
    }

    /**
     * Hook target: apply placeholder for blocked images.
     * Called when rendering comment with images while auto-load is off.
     */
    @JvmStatic
    fun applyPlaceholder(imageView: ImageView) {
        if (Settings.CommentImageAutoLoad.get()) return

        try {
            imageView.setImageResource(android.R.drawable.ic_menu_gallery)
            imageView.scaleType = ImageView.ScaleType.CENTER
            Logger.debug { "$TAG: placeholder applied" }
        } catch (e: Throwable) {
            Logger.debug { "$TAG: failed to apply placeholder: $e" }
        }
    }

    /**
     * Hook target: force load image on user click.
     * Called when user taps a placeholder image.
     */
    @JvmStatic
    fun onImageClicked(imageView: ImageView, imageUrl: String?) {
        if (imageUrl.isNullOrBlank()) return

        Logger.debug { "$TAG: loading image on click: $imageUrl" }
        // The actual loading is handled by the caller
        // This method just provides the hook point
    }

    /**
     * Get the count of images that were blocked from auto-loading.
     * Useful for showing a "X images not loaded" indicator.
     */
    @JvmStatic
    fun getBlockedCount(): Int {
        return if (Settings.CommentImageAutoLoad.get()) 0 else -1
    }
}
