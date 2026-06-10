package app.revanced.bilibili.patches

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.Reflex

@Keep
object PlaybackStatsOverlayPatch {

    private var overlayView: TextView? = null
    private var windowManager: WindowManager? = null
    private val handler = Handler(Looper.getMainLooper())
    private var isShowing = false

    @SuppressLint("SetTextI18n")
    @JvmStatic
    fun showOverlay(activity: Activity, player: Any?) {
        if (!Settings.PlaybackStatsOverlay.get()) return
        if (isShowing) return

        try {
            windowManager = activity.getSystemService(Activity.WINDOW_SERVICE) as WindowManager

            val params = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT
            ).apply {
                gravity = Gravity.TOP or Gravity.END
                x = 16
                y = 100
            }

            overlayView = TextView(activity).apply {
                setTextColor(Color.WHITE)
                textSize = 10f
                setPadding(12, 6, 12, 6)
                setBackgroundColor(Color.argb(180, 0, 0, 0))
                text = "Loading stats..."
            }

            windowManager?.addView(overlayView, params)
            isShowing = true

            // Start periodic updates
            startUpdating(player)
            Logger.debug { "PlaybackStatsOverlayPatch: overlay shown" }
        } catch (e: Throwable) {
            Logger.error(e) { "PlaybackStatsOverlayPatch: failed to show overlay" }
        }
    }

    private fun startUpdating(player: Any?) {
        val updateRunnable = object : Runnable {
            override fun run() {
                if (!isShowing) return
                updateStats(player)
                handler.postDelayed(this, 1000) // Update every second
            }
        }
        handler.post(updateRunnable)
    }

    private fun updateStats(player: Any?) {
        try {
            val stats = buildString {
                // Try to get FPS
                try {
                    val fps = Reflex.callMethod<Any>(player, "getVideoFps")
                    if (fps != null && fps != 0) append("FPS: $fps\n")
                } catch (e: Exception) { Logger.error { "Error in PlaybackStatsOverlayPatch: ${e.message}" } }

                // Try to get buffer duration
                try {
                    val buffer = Reflex.callMethod<Any>(player, "getBufferDuration")
                    if (buffer != null) {
                        val bufferMs = if (buffer is Long) buffer else (buffer as Int).toLong()
                        append("缓冲: ${bufferMs / 1000}s\n")
                    }
                } catch (e: Exception) { Logger.error { "Error in PlaybackStatsOverlayPatch: ${e.message}" } }

                // Try to get bitrate
                try {
                    val bitrate = Reflex.callMethod<Any>(player, "getVideoBitrate")
                    if (bitrate != null && bitrate != 0) {
                        val br = if (bitrate is Long) bitrate else (bitrate as Int).toLong()
                        append("码率: ${br / 1000}kbps\n")
                    }
                } catch (e: Exception) { Logger.error { "Error in PlaybackStatsOverlayPatch: ${e.message}" } }

                // Try to get network speed
                try {
                    val speed = Reflex.callMethod<Any>(player, "getDownloadSpeed")
                    if (speed != null && speed != 0) {
                        val sp = if (speed is Long) speed else (speed as Int).toLong()
                        append("网速: ${sp / 1024}KB/s")
                    }
                } catch (e: Exception) { Logger.error { "Error in PlaybackStatsOverlayPatch: ${e.message}" } }
            }.trim()

            if (stats.isNotEmpty()) {
                overlayView?.text = stats
            }
        } catch (e: Throwable) {
            Logger.error(e) { "PlaybackStatsOverlayPatch: failed to update stats" }
        }
    }

    @JvmStatic
    fun hideOverlay() {
        if (!isShowing) return
        try {
            handler.removeCallbacksAndMessages(null)
            overlayView?.let { windowManager?.removeView(it) }
            overlayView = null
            isShowing = false
            Logger.debug { "PlaybackStatsOverlayPatch: overlay hidden" }
        } catch (e: Throwable) {
            Logger.error(e) { "PlaybackStatsOverlayPatch: failed to hide overlay" }
        }
    }
}
