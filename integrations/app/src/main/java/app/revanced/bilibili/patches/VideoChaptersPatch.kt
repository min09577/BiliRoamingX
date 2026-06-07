package app.revanced.bilibili.patches

import android.app.AlertDialog
import android.app.Activity
import android.widget.ArrayAdapter
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object VideoChaptersPatch {

    data class Chapter(
        val title: String,
        val startTimeMs: Long,
        val endTimeMs: Long
    )

    @JvmStatic
    fun showChapters(activity: Activity?, chapters: List<Chapter>, onJump: (Long) -> Unit) {
        if (!Settings.VideoChapters.get()) return
        if (activity == null || chapters.isEmpty()) return

        try {
            val items = chapters.map { "${formatTime(it.startTimeMs)} - ${it.title}" }

            AlertDialog.Builder(activity)
                .setTitle("视频章节 (${chapters.size})")
                .setAdapter(ArrayAdapter(activity, android.R.layout.simple_list_item_1, items)) { _, which ->
                    onJump(chapters[which].startTimeMs)
                }
                .setNegativeButton("关闭", null)
                .show()

            Logger.debug { "VideoChapters: showed ${chapters.size} chapters" }
        } catch (e: Throwable) {
            Logger.error(e) { "VideoChapters: failed to show" }
        }
    }

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.VideoChapters.get()
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
