package app.revanced.bilibili.patches

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import java.text.SimpleDateFormat
import java.util.*

@Keep
object WatchStatsPatch {

    private const val PREFS_NAME = "watch_stats"
    private const val KEY_TOTAL_MS = "total_watch_ms"
    private const val KEY_DAILY_PREFIX = "daily_"
    private const val KEY_SESSION_START = "session_start"

    private var sessionStartMs = 0L
    private var lastUpdateMs = 0L

    @JvmStatic
    fun onVideoPlay(context: Context?) {
        if (!Settings.WatchStats.get()) return
        if (context == null) return

        sessionStartMs = System.currentTimeMillis()
        Logger.debug { "WatchStatsPatch: session started" }
    }

    @JvmStatic
    fun onVideoPause(context: Context?) {
        if (!Settings.WatchStats.get()) return
        if (context == null || sessionStartMs == 0L) return

        val duration = System.currentTimeMillis() - sessionStartMs
        if (duration > 1000) { // Only count if more than 1 second
            saveWatchTime(context, duration)
            Logger.debug { "WatchStatsPatch: recorded ${duration / 1000}s" }
        }
        sessionStartMs = 0L
    }

    @JvmStatic
    fun onVideoStop(context: Context?) {
        onVideoPause(context)
    }

    private fun saveWatchTime(context: Context, durationMs: Long) {
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

            // Update total
            val total = prefs.getLong(KEY_TOTAL_MS, 0) + durationMs
            prefs.edit().putLong(KEY_TOTAL_MS, total).apply()

            // Update daily
            val dailyKey = "$KEY_DAILY_PREFIX$today"
            val daily = prefs.getLong(dailyKey, 0) + durationMs
            prefs.edit().putLong(dailyKey, daily).apply()
        } catch (e: Throwable) {
            Logger.error(e) { "WatchStatsPatch: failed to save watch time" }
        }
    }

    @JvmStatic
    fun getTodayWatchTime(context: Context?): Long {
        if (context == null) return 0
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        return prefs.getLong("$KEY_DAILY_PREFIX$today", 0)
    }

    @JvmStatic
    fun getTotalWatchTime(context: Context?): Long {
        if (context == null) return 0
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getLong(KEY_TOTAL_MS, 0)
    }

    @JvmStatic
    fun formatDuration(ms: Long): String {
        val seconds = ms / 1000
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        return when {
            hours > 0 -> "${hours}小时${minutes}分钟"
            minutes > 0 -> "${minutes}分钟"
            else -> "${seconds}秒"
        }
    }
}
