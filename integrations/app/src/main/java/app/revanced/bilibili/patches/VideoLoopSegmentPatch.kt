package app.revanced.bilibili.patches

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.Toasts
import tv.danmaku.ijk.media.player.IMediaPlayer

@Keep
object VideoLoopSegmentPatch {
    private var loopStartMs: Long = -1L
    private var loopEndMs: Long = -1L
    private var isLooping = false
    private var currentVideoAid: Long = 0L

    /**
     * Set loop start point (A point).
     */
    @JvmStatic
    fun setLoopStart(player: IMediaPlayer?) {
        if (!Settings.VideoLoopSegment.get()) return
        if (player == null) return

        loopStartMs = player.currentPosition
        if (loopEndMs > 0 && loopStartMs >= loopEndMs) {
            loopEndMs = -1L
            Toasts.showShort("起点已设置，终点已重置（起点在终点之后）")
        } else {
            Toasts.showShort("循环起点: ${formatTime(loopStartMs)}")
        }
        Logger.debug { "VideoLoopSegment: start set to $loopStartMs" }
    }

    /**
     * Set loop end point (B point).
     */
    @JvmStatic
    fun setLoopEnd(player: IMediaPlayer?) {
        if (!Settings.VideoLoopSegment.get()) return
        if (player == null) return

        loopEndMs = player.currentPosition
        if (loopStartMs > 0 && loopEndMs <= loopStartMs) {
            loopEndMs = -1L
            Toasts.showShort("终点必须在起点之后")
        } else {
            Toasts.showShort("循环终点: ${formatTime(loopEndMs)}")
        }
        Logger.debug { "VideoLoopSegment: end set to $loopEndMs" }
    }

    /**
     * Start or stop A-B loop.
     */
    @JvmStatic
    fun toggleLoop(player: IMediaPlayer?) {
        if (!Settings.VideoLoopSegment.get()) return
        if (player == null) return

        if (isLooping) {
            stopLoop()
            Toasts.showShort("A-B循环已停止")
        }

        if (loopStartMs < 0 || loopEndMs < 0) {
            Toasts.showShort("请先设置循环起点和终点")
        }

        isLooping = true
        player.seekTo(loopStartMs)
        Toasts.showShort("A-B循环开始: ${formatTime(loopStartMs)} → ${formatTime(loopEndMs)}")
        Logger.debug { "VideoLoopSegment: loop started $loopStartMs -> $loopEndMs" }
    }

    /**
     * Check if we need to seek back to loop start.
     * Call this from player progress update.
     */
    @JvmStatic
    fun checkLoopPosition(player: IMediaPlayer?) {
        if (!isLooping || player == null) return
        if (loopStartMs < 0 || loopEndMs < 0) return

        val currentPos = player.currentPosition
        if (currentPos >= loopEndMs) {
            player.seekTo(loopStartMs)
            Logger.debug { "VideoLoopSegment: looped back to $loopStartMs" }
        }
    }

    /**
     * Stop looping.
     */
    @JvmStatic
    fun stopLoop() {
        isLooping = false
        Logger.debug { "VideoLoopSegment: loop stopped" }
    }

    /**
     * Reset all loop state when video changes.
     */
    @JvmStatic
    fun onVideoChanged(aid: Long) {
        if (aid != currentVideoAid) {
            currentVideoAid = aid
            loopStartMs = -1L
            loopEndMs = -1L
            isLooping = false
        }
    }

    /**
     * Get current loop state for display.
     */
    @JvmStatic
    fun getLoopStateText(): String {
        if (!Settings.VideoLoopSegment.get()) return ""
        return when {
            isLooping -> "循环中: ${formatTime(loopStartMs)} → ${formatTime(loopEndMs)}"
            loopStartMs >= 0 && loopEndMs >= 0 -> "已设置: ${formatTime(loopStartMs)} → ${formatTime(loopEndMs)}"
            loopStartMs >= 0 -> "起点: ${formatTime(loopStartMs)}"
            else -> ""
        }
    }

    /**
     * Show A-B loop settings dialog.
     */
    @JvmStatic
    fun showSettingsDialog(context: Context) {
        if (!Settings.VideoLoopSegment.get()) {
            Toasts.showShort("请先开启A-B循环功能")
        }

        val items = arrayOf(
            "设置起点 (A): ${if (loopStartMs >= 0) formatTime(loopStartMs) else "未设置"}",
            "设置终点 (B): ${if (loopEndMs >= 0) formatTime(loopEndMs) else "未设置"}",
            "手动输入时间",
            "重置循环点"
        )

        AlertDialog.Builder(context)
            .setTitle("A-B循环设置")
            .setItems(items) { _, which ->
                when (which) {
                    0 -> {
                        // Set start point - will be set when player is available
                        Toasts.showShort("请在播放时使用长按菜单设置起点")
                    }
                    1 -> {
                        // Set end point - will be set when player is available
                        Toasts.showShort("请在播放时使用长按菜单设置终点")
                    }
                    2 -> showManualInputDialog(context)
                    3 -> {
                        loopStartMs = -1L
                        loopEndMs = -1L
                        isLooping = false
                        Toasts.showShort("循环点已重置")
                    }
                }
            }
            .setNegativeButton("关闭", null)
            .show()
    }

    /**
     * Show manual time input dialog.
     */
    private fun showManualInputDialog(context: Context) {
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 20, 50, 20)
        }

        val startInput = EditText(context).apply {
            hint = "起点时间 (秒或 mm:ss)"
            setText(if (loopStartMs >= 0) "${loopStartMs / 1000}" else "")
        }
        val endInput = EditText(context).apply {
            hint = "终点时间 (秒或 mm:ss)"
            setText(if (loopEndMs >= 0) "${loopEndMs / 1000}" else "")
        }

        layout.addView(TextView(context).apply { text = "起点:" })
        layout.addView(startInput)
        layout.addView(TextView(context).apply { text = "终点:" })
        layout.addView(endInput)

        AlertDialog.Builder(context)
            .setTitle("手动输入循环时间")
            .setView(layout)
            .setPositiveButton("确定") { _, _ ->
                val startMs = parseTimeMs(startInput.text.toString())
                val endMs = parseTimeMs(endInput.text.toString())

                if (startMs < 0 || endMs < 0) {
                    Toasts.showShort("请输入有效的时间")
                    return@setPositiveButton
                }
                if (endMs <= startMs) {
                    Toasts.showShort("终点必须在起点之后")
                    return@setPositiveButton
                }

                loopStartMs = startMs
                loopEndMs = endMs
                Toasts.showShort("循环区间: ${formatTime(loopStartMs)} → ${formatTime(loopEndMs)}")
            }
            .setNegativeButton("取消", null)
            .show()
    }

    /**
     * Parse time string to milliseconds.
     * Supports: "123" (seconds), "1:23" (mm:ss), "1:23:45" (hh:mm:ss)
     */
    private fun parseTimeMs(timeStr: String): Long {
        val str = timeStr.trim()
        if (str.isEmpty()) return -1L

        return try {
            if (str.contains(":")) {
                val parts = str.split(":")
                when (parts.size) {
                    2 -> {
                        val min = parts[0].toLong()
                        val sec = parts[1].toLong()
                        (min * 60 + sec) * 1000
                    }
                    3 -> {
                        val hour = parts[0].toLong()
                        val min = parts[1].toLong()
                        val sec = parts[2].toLong()
                        (hour * 3600 + min * 60 + sec) * 1000
                    }
                    else -> -1L
                }
            } else {
                val seconds = str.toLong()
                seconds * 1000
            }
        } catch (e: Throwable) {
            -1L
        }
    }

    /**
     * Format milliseconds to mm:ss or hh:mm:ss.
     */
    private fun formatTime(ms: Long): String {
        val totalSeconds = ms / 1000
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60
        return if (hours > 0) {
            String.format("%d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format("%d:%02d", minutes, seconds)
        }
    }

    /**
     * Check if A-B loop is currently active.
     */
    @JvmStatic
    fun isActive(): Boolean = isLooping

    /**
     * Get loop start time in ms.
     */
    @JvmStatic
    fun getLoopStart(): Long = loopStartMs

    /**
     * Get loop end time in ms.
     */
    @JvmStatic
    fun getLoopEnd(): Long = loopEndMs
}
