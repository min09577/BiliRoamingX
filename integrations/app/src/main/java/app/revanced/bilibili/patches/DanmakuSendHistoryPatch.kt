package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 弹幕发送历史 — 记录最近发送的弹幕，方便快速重发
 * 保存最近20条发送的弹幕
 */
@Keep
object DanmakuSendHistoryPatch {
    private const val TAG = "DanmakuSendHistory"
    private const val MAX_HISTORY = 20
    // In-memory history (persisted separately)
    private val history = mutableListOf<String>()

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.DanmakuSendHistory.get()
    }

    @JvmStatic
    fun onDanmakuSent(text: String) {
        if (!isEnabled()) return
        if (text.isBlank()) return

        // Remove duplicate
        history.remove(text)
        history.add(0, text)

        // Trim to max size
        while (history.size > MAX_HISTORY) {
            history.removeAt(history.lastIndex)
        }
        Logger.debug { "$TAG: saved '$text', history size=${history.size}" }
    }

    @JvmStatic
    fun getHistory(): List<String> {
        return if (isEnabled()) history.toList() else emptyList()
    }

    @JvmStatic
    fun getHistoryCount(): Int {
        return history.size
    }

    @JvmStatic
    fun clearHistory() {
        history.clear()
        Logger.debug { "$TAG: history cleared" }
    }

    @JvmStatic
    fun removeHistoryItem(index: Int): Boolean {
        if (index < 0 || index >= history.size) return false
        history.removeAt(index)
        Logger.debug { "$TAG: removed item at $index" }
        return true
    }
}
