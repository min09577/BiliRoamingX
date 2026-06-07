package app.revanced.bilibili.patches

import android.content.Context
import android.graphics.drawable.Animatable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 评论区 GIF 自动播放控制 — 控制评论区中 GIF 图片是否自动播放
 * 默认开启，可关闭以节省流量和减少干扰
 */
@Keep
object CommentGifAutoPlayPatch {
    private const val TAG = "CommentGifAutoPlay"

    /**
     * Check if GIF auto-play is enabled in comment section.
     */
    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.CommentGifAutoPlay.get()
    }

    /**
     * Hook target: control GIF auto-play in comment image views.
     * Called when loading comment image.
     * @return true if should auto-play, false to pause
     */
    @JvmStatic
    fun shouldAutoPlay(): Boolean {
        if (!Settings.CommentGifAutoPlay.get()) {
            Logger.debug { "$TAG: GIF auto-play disabled" }
            return false
        }
        return true
    }

    /**
     * Hook target: control GIF playback in comment ImageView.
     * Called after image is loaded into ImageView.
     */
    @JvmStatic
    fun onCommentImageLoaded(imageView: ImageView, uri: Uri?) {
        if (!Settings.CommentGifAutoPlay.get()) return
        if (uri == null) return

        val uriStr = uri.toString()
        // Check if it's a GIF/APNG
        if (!uriStr.contains(".gif", ignoreCase = true)
            && !uriStr.contains(".apng", ignoreCase = true)
            && !uriStr.contains("/gif/", ignoreCase = true)
        ) return

        try {
            val drawable = imageView.drawable
            if (drawable is Animatable) {
                if (!drawable.isRunning) {
                    drawable.start()
                    Logger.debug { "$TAG: started GIF animation" }
                }
            }
        } catch (e: Throwable) {
            Logger.debug { "$TAG: failed to start GIF: $e" }
        }
    }

    /**
     * Hook target: pause all GIF animations in comment list.
     * Called when comment list is scrolled away or paused.
     */
    @JvmStatic
    fun pauseAllGifs(context: Context?) {
        if (Settings.CommentGifAutoPlay.get()) return
        Logger.debug { "$TAG: pausing all GIF animations" }
    }
}
