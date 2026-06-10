package app.revanced.bilibili.patches

import android.graphics.Bitmap
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 视频缩略图预览 — 在进度条悬停时显示视频缩略图预览
 * 方便快速定位视频内容
 */
@Keep
object VideoThumbnailPreviewPatch {
    private const val TAG = "ThumbnailPreview"
    // Thumbnail size
    private const val THUMB_WIDTH = 160
    private const val THUMB_HEIGHT = 90
    // Cache size (number of thumbnails to keep)
    private const val MAX_CACHE_SIZE = 50

    // LRU cache for thumbnails
    private val thumbnailCache = LinkedHashMap<Long, Bitmap>(MAX_CACHE_SIZE, 0.75f, true)

    /**
     * Check if thumbnail preview is enabled.
     */
    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.VideoThumbnailPreview.get()
    }

    /**
     * Hook target: generate thumbnail at specified position.
     * @param positionMs position in milliseconds
     * @return thumbnail bitmap or null
     */
    @JvmStatic
    fun getThumbnail(positionMs: Long): Bitmap? {
        if (!Settings.VideoThumbnailPreview.get()) return null

        // Check cache first
        synchronized(thumbnailCache) {
            thumbnailCache[positionMs]?.let {
                Logger.debug { "$TAG: cache hit at ${positionMs}ms" }
                return it
            }
        }

        Logger.debug { "$TAG: generating thumbnail at ${positionMs}ms" }
        // Actual thumbnail generation is handled by the hook caller
        return null
    }

    /**
     * Cache a generated thumbnail.
     */
    @JvmStatic
    fun cacheThumbnail(positionMs: Long, bitmap: Bitmap) {
        synchronized(thumbnailCache) {
            // Evict oldest if cache is full
            if (thumbnailCache.size >= MAX_CACHE_SIZE) {
                val oldest = thumbnailCache.keys.firstOrNull()
                oldest?.let { thumbnailCache.remove(it) }
            }
            thumbnailCache[positionMs] = bitmap
            Logger.debug { "$TAG: cached thumbnail at ${positionMs}ms, cache size: ${thumbnailCache.size}" }
        }
    }

    /**
     * Clear thumbnail cache (e.g., when video changes).
     */
    @JvmStatic
    fun clearCache() {
        synchronized(thumbnailCache) {
            thumbnailCache.clear()
            Logger.debug { "$TAG: cache cleared" }
        }
    }

    /**
     * Get the expected thumbnail dimensions.
     */
    @JvmStatic
    fun getThumbnailSize(): Pair<Int, Int> {
        return Pair(THUMB_WIDTH, THUMB_HEIGHT)
    }

    /**
     * Hook target: snap position to thumbnail interval.
     * Thumbnails are generated every N seconds for efficiency.
     */
    @JvmStatic
    fun snapToInterval(positionMs: Long, intervalMs: Long = 5000L): Long {
        return (positionMs / intervalMs) * intervalMs
    }
}
