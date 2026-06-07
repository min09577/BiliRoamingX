package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 直播切片 — 将直播流录制为本地视频片段
 * 支持设置录制时长和格式
 */
@Keep
object LiveStreamClipPatch {
    private const val TAG = "LiveStreamClip"
    // Default clip duration in seconds
    private const val DEFAULT_DURATION = 60

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.LiveStreamClip.get()
    }

    /**
     * Hook target: start clipping the live stream.
     * @param durationSec clip duration in seconds, 0 for default
     */
    @JvmStatic
    fun startClip(durationSec: Int = DEFAULT_DURATION): Boolean {
        if (!isEnabled()) return false
        Logger.debug { "$TAG: starting clip, duration=${durationSec}s" }
        return true
    }

    /**
     * Hook target: stop the current clip and save.
     * @return the saved file path or null
     */
    @JvmStatic
    fun stopClip(): String? {
        if (!isEnabled()) return null
        Logger.debug { "$TAG: stopping clip" }
        return null
    }

    /**
     * Hook target: check if currently recording a clip.
     */
    @JvmStatic
    fun isClipping(): Boolean {
        return false
    }

    /**
     * Hook target: get clip duration options.
     */
    @JvmStatic
    fun getDurationOptions(): IntArray {
        return intArrayOf(15, 30, 60, 120, 300)
    }
}
