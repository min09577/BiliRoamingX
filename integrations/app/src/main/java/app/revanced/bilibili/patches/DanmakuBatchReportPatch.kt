package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.Toasts

/**
 * 弹幕批量举报 — 在弹幕列表中支持批量选择和举报违规弹幕
 * 与批量删除配合使用，方便管理弹幕
 */
@Keep
object DanmakuBatchReportPatch {
    private const val TAG = "DanmakuBatchReport"
    // Selected danmaku IDs for report
    private val selectedIds = mutableSetOf<Long>()
    private var isReportMode = false
    // Report reasons
    const val REASON_SPAM = 1
    const val REASON_ABUSE = 2
    const val REASON_ADVERTISEMENT = 3
    const val REASON_SPOILER = 4
    const val REASON_OTHER = 99

    /**
     * Check if batch report feature is enabled.
     */
    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.DanmakuBatchReport.get()
    }

    /**
     * Toggle report selection mode.
     */
    @JvmStatic
    fun toggleReportMode(): Boolean {
        if (!Settings.DanmakuBatchReport.get()) return false
        isReportMode = !isReportMode
        if (!isReportMode) {
            selectedIds.clear()
        }
        Logger.debug { "$TAG: report mode = $isReportMode" }
        return isReportMode
    }

    /**
     * Check if currently in report selection mode.
     */
    @JvmStatic
    fun isReportMode(): Boolean {
        return isReportMode && Settings.DanmakuBatchReport.get()
    }

    /**
     * Select or deselect a danmaku for reporting.
     * @return true if selected, false if deselected
     */
    @JvmStatic
    fun toggleSelection(danmakuId: Long): Boolean {
        if (!isReportMode()) return false
        val added = if (selectedIds.contains(danmakuId)) {
            selectedIds.remove(danmakuId)
            false
        } else {
            selectedIds.add(danmakuId)
            true
        }
        Logger.debug { "$TAG: danmaku $danmakuId ${if (added) "selected" else "deselected"}, total: ${selectedIds.size}" }
        return added
    }

    /**
     * Get currently selected count for report.
     */
    @JvmStatic
    fun getSelectedCount(): Int {
        return selectedIds.size
    }

    /**
     * Execute batch report with specified reason.
     * @param reason one of REASON_* constants
     * @return number of reported items
     */
    @JvmStatic
    fun executeBatchReport(reason: Int = REASON_SPAM): Int {
        if (!isReportMode() || selectedIds.isEmpty()) return 0

        val count = selectedIds.size
        Logger.debug { "$TAG: batch reporting $count danmaku, reason=$reason" }

        // The actual report is handled by the hook caller
        selectedIds.clear()
        isReportMode = false

        return count
    }

    /**
     * Get the list of selected danmaku IDs for report.
     */
    @JvmStatic
    fun getSelectedIds(): LongArray {
        return selectedIds.toLongArray()
    }

    /**
     * Select all visible danmaku for report.
     */
    @JvmStatic
    fun selectAll(visibleIds: List<Long>) {
        if (!isReportMode()) return
        selectedIds.addAll(visibleIds)
        Logger.debug { "$TAG: selected all ${visibleIds.size} visible, total: ${selectedIds.size}" }
    }

    /**
     * Clear all report selections.
     */
    @JvmStatic
    fun clearSelection() {
        selectedIds.clear()
        Logger.debug { "$TAG: selection cleared" }
    }

    /**
     * Get reason description for UI display.
     */
    @JvmStatic
    fun getReasonText(reason: Int): String {
        return when (reason) {
            REASON_SPAM -> "垃圾信息"
            REASON_ABUSE -> "辱骂攻击"
            REASON_ADVERTISEMENT -> "广告推广"
            REASON_SPOILER -> "剧透内容"
            else -> "其他原因"
        }
    }
}
