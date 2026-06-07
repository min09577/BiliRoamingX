package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object DanmakuMergePatch {

    private val recentDanmaku = LinkedHashMap<String, Long>(100, 0.75f, true)
    private const val MERGE_WINDOW_MS = 3000L // 3 seconds window
    private const val MAX_CACHE_SIZE = 200

    @JvmStatic
    fun shouldShowDanmaku(content: String?, sendTimeMs: Long): Boolean {
        if (!Settings.DanmakuMerge.get()) return true
        if (content.isNullOrEmpty()) return true

        try {
            val key = content.trim()
            val lastTime = recentDanmaku[key]

            if (lastTime != null) {
                val timeDiff = sendTimeMs - lastTime
                if (timeDiff < MERGE_WINDOW_MS) {
                    // Duplicate within merge window, hide it
                    Logger.debug { "DanmakuMerge: merged duplicate: $key" }
                    return false
                }
            }

            // Update cache
            recentDanmaku[key] = sendTimeMs

            // Cleanup old entries
            if (recentDanmaku.size > MAX_CACHE_SIZE) {
                val iterator = recentDanmaku.entries.iterator()
                var removed = 0
                while (iterator.hasNext() && removed < 50) {
                    iterator.next()
                    iterator.remove()
                    removed++
                }
            }

            return true
        } catch (e: Throwable) {
            Logger.error(e) { "DanmakuMerge: check failed" }
            return true
        }
    }

    @JvmStatic
    fun clearCache() {
        recentDanmaku.clear()
    }
}
