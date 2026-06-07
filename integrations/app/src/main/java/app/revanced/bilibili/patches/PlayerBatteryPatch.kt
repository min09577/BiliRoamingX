package app.revanced.bilibili.patches

import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.BatteryManager
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object PlayerBatteryPatch {

    private var batteryView: TextView? = null
    private var windowManager: WindowManager? = null
    private var isShowing = false
    private val handler = Handler(Looper.getMainLooper())
    private var batteryReceiver: BroadcastReceiver? = null

    @SuppressLint("SetTextI18n")
    @JvmStatic
    fun showBattery(activity: Activity) {
        if (!Settings.PlayerBattery.get()) return
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
                gravity = Gravity.TOP or Gravity.START
                x = 16
                y = 16
            }

            batteryView = TextView(activity).apply {
                setTextColor(Color.WHITE)
                textSize = 11f
                setPadding(8, 4, 8, 4)
                setBackgroundColor(Color.argb(120, 0, 0, 0))
                text = "🔋 ..."
            }

            windowManager?.addView(batteryView, params)
            isShowing = true

            // Register battery receiver
            batteryReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
                    val scale = intent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
                    if (level >= 0 && scale > 0) {
                        val pct = (level * 100 / scale)
                        val icon = when {
                            pct >= 80 -> "🔋"
                            pct >= 50 -> "🔋"
                            pct >= 20 -> "🪫"
                            else -> "🪫"
                        }
                        batteryView?.text = "$icon $pct%"
                    }
                }
            }
            val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            activity.registerReceiver(batteryReceiver, filter)

            Logger.debug { "PlayerBattery: shown" }
        } catch (e: Throwable) {
            Logger.error(e) { "PlayerBattery: failed to show" }
        }
    }

    @JvmStatic
    fun hideBattery(activity: Activity?) {
        if (!isShowing) return
        try {
            batteryReceiver?.let { activity?.unregisterReceiver(it) }
            batteryReceiver = null
            batteryView?.let { windowManager?.removeView(it) }
            batteryView = null
            isShowing = false
            Logger.debug { "PlayerBattery: hidden" }
        } catch (e: Throwable) {
            Logger.error(e) { "PlayerBattery: failed to hide" }
        }
    }
}
