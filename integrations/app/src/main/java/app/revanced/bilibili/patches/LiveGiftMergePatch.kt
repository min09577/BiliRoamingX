package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 直播礼物合并 — 将短时间内相同用户的相同礼物合并显示，
 * 减少礼物弹幕刷屏
 */
@Keep
object LiveGiftMergePatch {
    private const val TAG = "LiveGiftMerge"
    // Merge window in ms — gifts from same user within this window are merged
    private const val MERGE_WINDOW_MS = 3000L
    // Max merge count
    private const val MAX_MERGE_COUNT = 99

    data class GiftEntry(
        val userId: Long,
        val giftName: String,
        val giftId: Int,
        var count: Int,
        var timestamp: Long
    )

    // Cache for pending gifts to merge
    private val pendingGifts = mutableListOf<GiftEntry>()

    /**
     * Check if gift merge is enabled.
     */
    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.LiveGiftMerge.get()
    }

    /**
     * Process a gift event and decide if it should be merged.
     * Returns merged count if merged, or -1 if this is a new gift (should display normally).
     */
    @JvmStatic
    fun processGift(userId: Long, giftName: String, giftId: Int, count: Int): Int {
        if (!Settings.LiveGiftMerge.get()) return -1

        val now = System.currentTimeMillis()

        synchronized(pendingGifts) {
            // Find existing entry from same user with same gift within merge window
            val existing = pendingGifts.find {
                it.userId == userId && it.giftId == giftId
                    && (now - it.timestamp) < MERGE_WINDOW_MS
            }

            if (existing != null) {
                existing.count = (existing.count + count).coerceAtMost(MAX_MERGE_COUNT)
                existing.timestamp = now
                Logger.debug { "$TAG: merged gift from $userId: $giftName x${existing.count}" }
                return existing.count
            }

            // New gift, add to pending
            pendingGifts.add(GiftEntry(userId, giftName, giftId, count, now))

            // Cleanup old entries
            pendingGifts.removeAll { (now - it.timestamp) > MERGE_WINDOW_MS * 2 }

            return -1
        }
    }

    /**
     * Format merged gift display text.
     * e.g. "送出 99x 小电视飞船"
     */
    @JvmStatic
    fun formatMergedGiftText(giftName: String, count: Int): String {
        return if (count > 1) "${count}x $giftName" else giftName
    }

    /**
     * Clear merge cache (e.g., when entering/exiting a live room).
     */
    @JvmStatic
    fun clearCache() {
        synchronized(pendingGifts) {
            pendingGifts.clear()
            Logger.debug { "$TAG: cache cleared" }
        }
    }
}
