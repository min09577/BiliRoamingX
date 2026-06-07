package app.revanced.bilibili.patches

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.graphics.PixelFormat
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object NetworkIndicatorPatch {

    private var indicatorView: TextView? = null
    private var windowManager: WindowManager? = null
    private var isShowing = false
    private val handler = Handler(Looper.getMainLooper())
    private var lastRxBytes = 0L
    private var lastTime = 0L

    @SuppressLint("SetTextI18n")
    @JvmStatic
    fun showIndicator(activity: Activity) {
        if (!Settings.NetworkIndicator.get()) return
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
                gravity = Gravity.BOTTOM or Gravity.END
                x = 16
                y = 100
            }

            indicatorView = TextView(activity).apply {
                setTextColor(Color.WHITE)
                textSize = 10f
                setPadding(8, 4, 8, 4)
                setBackgroundColor(Color.argb(150, 76, 175, 80))
                text = "📶 ..."
            }

            windowManager?.addView(indicatorView, params)
            isShowing = true
            lastRxBytes = android.net.TrafficStats.getTotalRxBytes()
            lastTime = System.currentTimeMillis()

            startUpdating(activity)
            Logger.debug { "NetworkIndicator: shown" }
        } catch (e: Throwable) {
            Logger.error(e) { "NetworkIndicator: failed to show" }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun startUpdating(activity: Activity) {
        val updateRunnable = object : Runnable {
            @SuppressLint("SetTextI18n")
            override fun run() {
                if (!isShowing) return
                try {
                    val currentRx = android.net.TrafficStats.getTotalRxBytes()
                    val currentTime = System.currentTimeMillis()
                    val timeDiff = (currentTime - lastTime) / 1000.0

                    if (timeDiff > 0) {
                        val speedBytes = ((currentRx - lastRxBytes) / timeDiff).toLong()
                        val speedKB = speedBytes / 1024
                        val speedMB = speedKB / 1024.0

                        val speedText = if (speedMB >= 1) {
                            String.format("%.1fMB/s", speedMB)
                        } else {
                            "${speedKB}KB/s"
                        }

                        val networkType = getNetworkType(activity)
                        indicatorView?.text = "📶 $networkType $speedText"

                        lastRxBytes = currentRx
                        lastTime = currentTime
                    }
                } catch (e: Throwable) {
                    Logger.error(e) { "NetworkIndicator: update failed" }
                }
                handler.postDelayed(this, 2000)
            }
        }
        handler.post(updateRunnable)
    }

    private fun getNetworkType(activity: Activity): String {
        try {
            val cm = activity.getSystemService(Activity.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = cm.activeNetwork ?: return "无网络"
            val caps = cm.getNetworkCapabilities(network) ?: return "无网络"

            return when {
                caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> "WiFi"
                caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> "移动"
                caps.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> "以太网"
                else -> "其他"
            }
        } catch (e: Throwable) {
            return "未知"
        }
    }

    @JvmStatic
    fun hideIndicator() {
        if (!isShowing) return
        try {
            handler.removeCallbacksAndMessages(null)
            indicatorView?.let { windowManager?.removeView(it) }
            indicatorView = null
            isShowing = false
            Logger.debug { "NetworkIndicator: hidden" }
        } catch (e: Throwable) {
            Logger.error(e) { "NetworkIndicator: failed to hide" }
        }
    }
}
