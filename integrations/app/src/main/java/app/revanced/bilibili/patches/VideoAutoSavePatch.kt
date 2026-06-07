package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 视频自动收藏 — 播放视频时自动将其添加到"稍后再看"列表
 * 方便稍后回看感兴趣的内容
 */
@Keep
object VideoAutoSavePatch {
    private const val TAG = "VideoAutoSave"
    // Cache of recently saved videos to avoid duplicate saves
    private val recentlySaved = mutableSetOf<Long>()
    // Max cache size
    private const val MAX_CACHE = 100

    /**
     * Check if auto-save is enabled.
     */
    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.VideoAutoSave.get()
    }

    /**
     * Hook target: auto-save video to watch later list.
     * Called when video starts playing.
     * @param videoId the video/bv ID to save
     * @return true if saved, false if already saved or feature disabled
     */
    @JvmStatic
    fun autoSave(videoId: Long): Boolean {
        if (!Settings.VideoAutoSave.get()) return false

        // Avoid duplicate saves
        if (recentlySaved.contains(videoId)) {
            Logger.debug { "$TAG: video $videoId already saved recently" }
            return false
        }

        // Add to recently saved cache
        if (recentlySaved.size >= MAX_CACHE) {
            recentlySaved.clear()
        }
        recentlySaved.add(videoId)

        Logger.debug { "$TAG: auto-saving video $videoId" }
        return true
    }

    /**
     * Hook target: auto-save video by String ID.
     * @param videoId the video ID as string
     * @return true if saved
     */
    @JvmStatic
    fun autoSaveById(videoId: String): Boolean {
        val id = videoId.toLongOrNull() ?: return false
        return autoSave(id)
    }

    /**
     * Check if a video was recently auto-saved.
     */
    @JvmStatic
    fun wasRecentlySaved(videoId: Long): Boolean {
        return recentlySaved.contains(videoId)
    }

    /**
     * Clear the recently saved cache.
     */
    @JvmStatic
    fun clearCache() {
        recentlySaved.clear()
        Logger.debug { "$TAG: cache cleared" }
    }

    /**
     * Get the number of recently saved videos.
     */
    @JvmStatic
    fun getSavedCount(): Int {
        return recentlySaved.size
    }
}
