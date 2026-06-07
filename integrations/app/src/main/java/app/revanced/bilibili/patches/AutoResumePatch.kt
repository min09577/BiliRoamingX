package app.revanced.bilibili.patches

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.Toasts

@Keep
object AutoResumePatch {

    private const val PREFS_NAME = "auto_resume"
    private const val KEY_PREFIX = "pos_"

    @JvmStatic
    fun onSavePosition(context: Context?, videoId: String?, positionMs: Long) {
        if (!Settings.AutoResume.get()) return
        if (context == null || videoId == null || positionMs <= 0) return

        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putLong("$KEY_PREFIX$videoId", positionMs).apply()
            Logger.debug { "AutoResume: saved position $positionMs for $videoId" }
        } catch (e: Throwable) {
            Logger.error(e) { "AutoResume: failed to save" }
        }
    }

    @JvmStatic
    fun onVideoOpen(context: Context?, videoId: String?, callback: ((Long) -> Unit)?) {
        if (!Settings.AutoResume.get()) return
        if (context == null || videoId == null) return

        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val savedPos = prefs.getLong("$KEY_PREFIX$videoId", 0)

            if (savedPos > 0) {
                val seconds = savedPos / 1000
                val timeStr = formatTime(savedPos)

                AlertDialog.Builder(context)
                    .setTitle("继续播放")
                    .setMessage("上次播放到 $timeStr，是否继续？")
                    .setPositiveButton("继续") { _, _ ->
                        callback?.invoke(savedPos)
                        Toasts.showShort("已跳转到 $timeStr")
                    }
                    .setNegativeButton("从头播放") { _, _ ->
                        callback?.invoke(0)
                    }
                    .show()

                Logger.debug { "AutoResume: showed resume dialog for $videoId at $savedPos" }
            }
        } catch (e: Throwable) {
            Logger.error(e) { "AutoResume: failed to check" }
        }
    }

    @JvmStatic
    fun clearPosition(context: Context?, videoId: String?) {
        if (context == null || videoId == null) return
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().remove("$KEY_PREFIX$videoId").apply()
        } catch (_: Throwable) {}
    }

    private fun formatTime(ms: Long): String {
        val totalSeconds = ms / 1000
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60
        return if (hours > 0) {
            String.format("%d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format("%02d:%02d", minutes, seconds)
        }
    }
}
