package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 直播间自动进入 — 启动App时自动进入指定直播间
 * 配置格式：直播间ID（数字）或直播间URL
 */
@Keep
object LiveRoomAutoEnterPatch {
    private const val TAG = "LiveRoomAutoEnter"

    /**
     * Check if auto-enter is enabled (setting is non-empty).
     */
    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.LiveRoomAutoEnter.get().isNotBlank()
    }

    /**
     * Get the configured room ID to auto-enter.
     * Parses from various formats: pure number, URL, or short link.
     * @return room ID as Long, or 0 if not configured/invalid
     */
    @JvmStatic
    fun getTargetRoomId(): Long {
        val config = Settings.LiveRoomAutoEnter.get().trim()
        if (config.isBlank()) return 0L

        // Try pure number
        config.toLongOrNull()?.let { return it }

        // Try URL format: https://live.bilibili.com/12345
        val urlPattern = Regex("""live\.bilibili\.com/(\d+)""")
        urlPattern.find(config)?.let {
            return it.groupValues[1].toLongOrNull() ?: 0L
        }

        // Try short format: live:12345
        if (config.startsWith("live:", ignoreCase = true)) {
            return config.substringAfter("live:").toLongOrNull() ?: 0L
        }

        Logger.debug { "$TAG: invalid room config: $config" }
        return 0L
    }

    /**
     * Hook target: get the auto-enter room URL.
     * Called during app startup to check if should navigate to live room.
     * @return full live room URL, or null if not configured
     */
    @JvmStatic
    fun getAutoEnterUrl(): String? {
        val roomId = getTargetRoomId()
        if (roomId <= 0) return null

        val url = "https://live.bilibili.com/$roomId"
        Logger.debug { "$TAG: auto-enter URL: $url" }
        return url
    }

    /**
     * Hook target: check and perform auto-enter.
     * Called when main activity is resumed.
     * @return true if should navigate to live room
     */
    @JvmStatic
    fun shouldAutoEnter(): Boolean {
        if (!isEnabled()) return false
        val roomId = getTargetRoomId()
        val should = roomId > 0
        Logger.debug { "$TAG: shouldAutoEnter=$should, roomId=$roomId" }
        return should
    }
}
