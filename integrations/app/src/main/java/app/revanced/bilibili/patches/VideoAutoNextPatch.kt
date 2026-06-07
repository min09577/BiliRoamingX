package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 自动播放下一集 — 当前视频播放结束后自动播放下一集
 * 适用于番剧、课程等多集内容
 */
@Keep
object VideoAutoNextPatch {
    private const val TAG = "VideoAutoNext"
    // Countdown before auto-playing next (seconds)
    private const val DEFAULT_COUNTDOWN = 5
    // Minimum remaining time to trigger next episode check (seconds)
    private const val TRIGGER_THRESHOLD = 3

    /**
     * Check if auto-next is enabled.
     */
    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.VideoAutoNext.get()
    }

    /**
     * Hook target: check if should auto-play next episode.
     * Called when video reaches near the end.
     * @param remainingSeconds seconds remaining in current video
     * @param hasNextEpisode whether there is a next episode available
     * @return countdown seconds to show, or -1 if should not trigger
     */
    @JvmStatic
    fun shouldAutoNext(remainingSeconds: Int, hasNextEpisode: Boolean): Int {
        if (!Settings.VideoAutoNext.get()) return -1
        if (!hasNextEpisode) return -1
        if (remainingSeconds > TRIGGER_THRESHOLD) return -1

        Logger.debug { "$TAG: triggering auto-next, ${remainingSeconds}s remaining" }
        return DEFAULT_COUNTDOWN
    }

    /**
     * Hook target: get the countdown duration.
     */
    @JvmStatic
    fun getCountdownSeconds(): Int {
        return DEFAULT_COUNTDOWN
    }

    /**
     * Hook target: called when user cancels auto-next.
     */
    @JvmStatic
    fun onCancelAutoNext() {
        Logger.debug { "$TAG: auto-next cancelled by user" }
    }

    /**
     * Hook target: called when auto-next executes.
     */
    @JvmStatic
    fun onAutoNextExecuted() {
        Logger.debug { "$TAG: auto-next executed, playing next episode" }
    }

    /**
     * Hook target: check if current content supports auto-next.
     * Only supports series content (bangumi, courses, etc.)
     * @param contentType the type of content being played
     * @return true if auto-next is supported for this content type
     */
    @JvmStatic
    fun isSupportedContentType(contentType: String?): Boolean {
        if (contentType == null) return false
        val supported = listOf("bangumi", "course", "series", "playlist", "ugc_season")
        val result = supported.any { contentType.contains(it, ignoreCase = true) }
        Logger.debug { "$TAG: content type '$contentType', supported=$result" }
        return result
    }
}
