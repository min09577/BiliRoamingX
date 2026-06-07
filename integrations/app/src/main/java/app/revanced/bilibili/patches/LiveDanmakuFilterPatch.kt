package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object LiveDanmakuFilterPatch {

    private val FILTER_PATTERNS = listOf(
        "^打卡$",
        "^签到$",
        "^6+$",
        "^\\[.+]$",  // Emoticons like [doge]
        "^哈哈+$",
        "^？+$",
        "^\\d+$",  // Pure numbers
    )

    @JvmStatic
    fun shouldFilter(content: String?): Boolean {
        if (!Settings.LiveDanmakuFilter.get()) return false
        if (content.isNullOrEmpty()) return false

        try {
            val trimmed = content.trim()

            // Filter by patterns
            for (pattern in FILTER_PATTERNS) {
                if (Regex(pattern).matches(trimmed)) {
                    Logger.debug { "LiveDanmakuFilter: filtered '$trimmed'" }
                    return true
                }
            }

            // Filter too short
            if (trimmed.length <= 2) {
                return true
            }

        } catch (e: Throwable) {
            Logger.error(e) { "LiveDanmakuFilter: check failed" }
        }
        return false
    }

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.LiveDanmakuFilter.get()
    }
}
