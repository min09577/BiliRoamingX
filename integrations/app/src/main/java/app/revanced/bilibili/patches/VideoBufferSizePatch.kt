package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

/**
 * 视频缓冲大小自定义 — 控制视频预加载缓冲时长
 * 0=默认, 1=低(10s), 2=中(30s), 3=高(60s), 4=极限(120s)
 */
@Keep
object VideoBufferSizePatch {
    private const val DEFAULT_BUFFER_SIZE_KB = 10240
    private const val TAG = "VideoBufferSize"
    const val BUFFER_DEFAULT = 0
    const val BUFFER_LOW = 1
    const val BUFFER_MEDIUM = 2
    const val BUFFER_HIGH = 3
    const val BUFFER_EXTREME = 4

    // Buffer durations in milliseconds
    private val BUFFER_DURATIONS = intArrayOf(0, 10000, 30000, 60000, 120000)

    @JvmStatic
    fun getBufferPreset(): Int {
        return Settings.VideoBufferSize.get().coerceIn(0, 4)
    }

    @JvmStatic
    fun getBufferDurationMs(): Int {
        return BUFFER_DURATIONS[getBufferPreset()]
    }

    @JvmStatic
    fun getBufferDurationSeconds(): Int {
        return getBufferDurationMs() / 1000
    }

    @JvmStatic
    fun getBufferDescription(): String {
        return when (getBufferPreset()) {
            BUFFER_LOW -> "低 (10秒)"
            BUFFER_MEDIUM -> "中 (30秒)"
            BUFFER_HIGH -> "高 (60秒)"
            BUFFER_EXTREME -> "极限 (120秒)"
            else -> "默认"
        }
    }
}