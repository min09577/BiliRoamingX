package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 按用户过滤弹幕 — 根据用户ID过滤特定用户的弹幕
 * 需要在设置中配置要过滤的用户ID列表
 */
@Keep
object DanmakuFilterByUserPatch {
    private const val TAG = "DanmakuFilterByUser"

    /**
     * Check if user-based filtering is enabled.
     */
    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.DanmakuFilterByUser.get().isNotEmpty()
    }

    /**
     * Check if a specific user's danmaku should be filtered.
     * @param userId the user ID who sent the danmaku
     * @return true if the danmaku should be filtered (hidden)
     */
    @JvmStatic
    fun shouldFilter(userId: String): Boolean {
        if (!isEnabled()) return false

        val filterSet = Settings.DanmakuFilterByUser.get()
        val shouldFilter = filterSet.contains(userId)
        if (shouldFilter) {
            Logger.debug { "$TAG: filtered danmaku from user $userId" }
        }
        return shouldFilter
    }

    /**
     * Check if a specific user ID should be filtered.
     * @param userId Long user ID
     * @return true if should filter
     */
    @JvmStatic
    fun shouldFilterById(userId: Long): Boolean {
        return shouldFilter(userId.toString())
    }

    /**
     * Add a user ID to the filter list.
     */
    @JvmStatic
    fun addToFilter(userId: String) {
        val current = Settings.DanmakuFilterByUser.get().toMutableSet()
        current.add(userId)
        Settings.DanmakuFilterByUser.set(current)
        Logger.debug { "$TAG: added $userId to filter, total: ${current.size}" }
    }

    /**
     * Remove a user ID from the filter list.
     */
    @JvmStatic
    fun removeFromFilter(userId: String) {
        val current = Settings.DanmakuFilterByUser.get().toMutableSet()
        current.remove(userId)
        Settings.DanmakuFilterByUser.set(current)
        Logger.debug { "$TAG: removed $userId from filter, total: ${current.size}" }
    }

    /**
     * Get all filtered user IDs.
     */
    @JvmStatic
    fun getFilteredUsers(): Set<String> {
        return Settings.DanmakuFilterByUser.get()
    }

    /**
     * Get the count of filtered users.
     */
    @JvmStatic
    fun getFilterCount(): Int {
        return Settings.DanmakuFilterByUser.get().size
    }
}
