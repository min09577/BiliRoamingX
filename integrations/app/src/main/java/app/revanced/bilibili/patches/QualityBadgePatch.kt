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
object QualityBadgePatch {

    private var badgeView: TextView? = null
    private var windowManager: WindowManager? = null
    private var isShowing = false

    @SuppressLint("SetTextI18n")
    @JvmStatic
    fun showBadge(activity: Activity, player: Any?, qualityName: String?) {
        if (!Settings.QualityBadge.get()) return
        if (isShowing) hideBadge()

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
                y = 100
            }

            val text = qualityName ?: try {
                val qn = Reflex.callMethod<Any>(player, "getCurrentQuality")
                when (qn) {
                    is Int -> getQualityName(qn)
                    is Long -> getQualityName(qn.toInt())
                    else -> "HD"
                }
            } catch (_: Throwable) { "HD" }

            badgeView = TextView(activity).apply {
                setTextColor(Color.WHITE)
                textSize = 11f
                setPadding(10, 4, 10, 4)
                setBackgroundColor(Color.argb(180, 33, 150, 243))
                this.text = "🎬 $text"
            }

            windowManager?.addView(badgeView, params)
            isShowing = true

            // Auto hide after 3 seconds
            Handler(Looper.getMainLooper()).postDelayed({ hideBadge() }, 3000)

            Logger.debug { "QualityBadge: showing $text" }
        } catch (e: Throwable) {
            Logger.error(e) { "QualityBadge: failed to show" }
        }
    }

    @JvmStatic
    fun hideBadge() {
        if (!isShowing) return
        try {
            badgeView?.let { windowManager?.removeView(it) }
            badgeView = null
            isShowing = false
        } catch (_: Throwable) {}
    }

    private fun getQualityName(qn: Int): String {
        return when (qn) {
            127 -> "8K"
            126 -> "杜比视界"
            125 -> "HDR"
            120 -> "4K"
            116 -> "1080P60"
            112 -> "1080P高码率"
            80 -> "1080P"
            74 -> "720P60"
            64 -> "720P"
            32 -> "480P"
            16 -> "360P"
            6 -> "240P"
            else -> "画质$qn"
        }
    }
}
