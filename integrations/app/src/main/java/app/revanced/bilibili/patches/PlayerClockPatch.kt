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
import java.text.SimpleDateFormat
import java.util.*

@Keep
object PlayerClockPatch {

    private var clockView: TextView? = null
    private var windowManager: WindowManager? = null
    private var isShowing = false
    private val handler = Handler(Looper.getMainLooper())
    private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    @SuppressLint("SetTextI18n")
    @JvmStatic
    fun showClock(activity: Activity) {
        if (!Settings.PlayerClock.get()) return
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
                y = 16
            }

            clockView = TextView(activity).apply {
                setTextColor(Color.WHITE)
                textSize = 12f
                setPadding(8, 4, 8, 4)
                setBackgroundColor(Color.argb(120, 0, 0, 0))
                text = timeFormat.format(Date())
            }

            windowManager?.addView(clockView, params)
            isShowing = true
            startUpdating()
            Logger.debug { "PlayerClock: clock shown" }
        } catch (e: Throwable) {
            Logger.error(e) { "PlayerClock: failed to show" }
        }
    }

    private fun startUpdating() {
        val updateRunnable = object : Runnable {
            override fun run() {
                if (!isShowing) return
                clockView?.text = timeFormat.format(Date())
                handler.postDelayed(this, 30000)
            }
        }
        handler.post(updateRunnable)
    }

    @JvmStatic
    fun hideClock() {
        if (!isShowing) return
        try {
            handler.removeCallbacksAndMessages(null)
            clockView?.let { windowManager?.removeView(it) }
            clockView = null
            isShowing = false
            Logger.debug { "PlayerClock: clock hidden" }
        } catch (e: Throwable) {
            Logger.error(e) { "PlayerClock: failed to hide" }
        }
    }
}
