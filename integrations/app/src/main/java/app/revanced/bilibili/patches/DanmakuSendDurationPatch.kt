package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 弹幕显示时长调节 — 控制弹幕在屏幕上飘过的速度
 * 值越小弹幕越快，值越大弹幕越慢
 */
@Keep
object DanmakuSendDurationPatch {
    private const val TAG = "DanmakuSendDuration"
    // Default duration in ms (bilibili default ~6000ms)
    private const val DEFAULT_DURATION = 6000
    // Min/Max bounds
    private const val MIN_DURATION = 2000
    private const val MAX_DURATION = 15000

    /**
     * Get the custom danmaku duration in milliseconds.
     * Returns 0 if not customized (use default).
     */
    @JvmStatic
    fun getDuration(): Int {
        val setting = Settings.DanmakuSendDuration.get()
        if (setting <= 0) return 0

        val duration = setting * 1000 // convert seconds to ms
        return duration.coerceIn(MIN_DURATION, MAX_DURATION)
    }

    /**
     * Get the duration multiplier relative to default.
     * Used for scaling danmaku scroll speed.
     */
    @JvmStatic
    fun getDurationMultiplier(): Float {
        val duration = getDuration()
        if (duration <= 0) return 1.0f
        return duration.toFloat() / DEFAULT_DURATION
    }

    /**
     * Hook target: modify danmaku render duration.
     * Called when creating danmaku renderer.
     */
    @JvmStatic
    fun modifyRenderDuration(originalDuration: Long): Long {
        val customDuration = getDuration()
        if (customDuration <= 0) return originalDuration

        Logger.debug { "$TAG: duration $originalDuration -> $customDuration" }
        return customDuration.toLong()
    }

    /**
     * Hook target: modify danmaku scroll speed.
     * Called when calculating danmaku velocity.
     */
    @JvmStatic
    fun modifyScrollSpeed(originalSpeed: Float): Float {
        val multiplier = getDurationMultiplier()
        if (multiplier == 1.0f) return originalSpeed

        // Slower duration = slower speed
        val newSpeed = originalSpeed / multiplier
        Logger.debug { "$TAG: speed $originalSpeed -> $newSpeed (multiplier=$multiplier)" }
        return newSpeed
    }

    /**
     * Hook target: modify danmaku cache duration for player.
     */
    @JvmStatic
    fun modifyCacheDuration(originalCache: Long): Long {
        val customDuration = getDuration()
        if (customDuration <= 0) return originalCache

        // Cache should be proportional to display duration
        return (customDuration * 1.5).toLong()
    }
}
