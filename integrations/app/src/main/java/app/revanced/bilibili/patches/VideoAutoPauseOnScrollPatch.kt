package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 滚动时自动暂停视频 — 在信息流中滚动时自动暂停正在播放的视频
 * 避免视频在后台继续播放消耗资源
 */
@Keep
object VideoAutoPauseOnScrollPatch {
    private const val TAG = "AutoPauseOnScroll"
    // Debounce time to avoid pausing during fast scroll
    private const val SCROLL_DEBOUNCE_MS = 300L
    private var lastScrollTime = 0L
    private var isPausedByScroll = false

    /**
     * Check if auto-pause on scroll is enabled.
     */
    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.VideoAutoPauseOnScroll.get()
    }

    /**
     * Hook target: called when user starts scrolling in feed.
     * Should pause any playing video.
     * @return true if a video was paused
     */
    @JvmStatic
    fun onScrollStart(): Boolean {
        if (!Settings.VideoAutoPauseOnScroll.get()) return false

        val now = System.currentTimeMillis()
        if (now - lastScrollTime < SCROLL_DEBOUNCE_MS) return false
        lastScrollTime = now

        Logger.debug { "$TAG: scroll detected, pausing video" }
        isPausedByScroll = true
        return true
    }

    /**
     * Hook target: called when scroll stops and user settles on content.
     * Can optionally resume the paused video.
     * @param shouldResume true to resume the paused video
     */
    @JvmStatic
    fun onScrollStop(shouldResume: Boolean = false) {
        if (!Settings.VideoAutoPauseOnScroll.get()) return

        if (isPausedByScroll && shouldResume) {
            Logger.debug { "$TAG: scroll stopped, resuming video" }
            isPausedByScroll = false
        } else if (isPausedByScroll) {
            Logger.debug { "$TAG: scroll stopped, video stays paused" }
            isPausedByScroll = false
        }
    }

    /**
     * Check if the video was paused by scroll (not by user).
     * Used to determine if auto-resume should be offered.
     */
    @JvmStatic
    fun isPausedByScroll(): Boolean {
        return isPausedByScroll
    }

    /**
     * Reset state (e.g., when leaving the feed).
     */
    @JvmStatic
    fun reset() {
        isPausedByScroll = false
        lastScrollTime = 0L
        Logger.debug { "$TAG: state reset" }
    }
}
