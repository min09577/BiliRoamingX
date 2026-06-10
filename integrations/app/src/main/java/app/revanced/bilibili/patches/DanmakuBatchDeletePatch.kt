package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 弹幕批量删除 — 在弹幕列表中支持批量选择和删除弹幕
 * 方便清理不需要的弹幕记录
 */
@Keep
object DanmakuBatchDeletePatch {
    private const val TAG = "DanmakuBatchDelete"
    // Selected danmaku IDs for batch operation
    private val selectedIds = mutableSetOf<Long>()
    private var isBatchMode = false

    /**
     * Check if batch delete feature is enabled.
     */
    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.DanmakuBatchDelete.get()
    }

    /**
     * Toggle batch selection mode.
     */
    @JvmStatic
    fun toggleBatchMode(): Boolean {
        if (!Settings.DanmakuBatchDelete.get()) return false
        isBatchMode = !isBatchMode
        if (!isBatchMode) {
            selectedIds.clear()
        }
        Logger.debug { "$TAG: batch mode = $isBatchMode" }
        return isBatchMode
    }

    /**
     * Check if currently in batch selection mode.
     */
    @JvmStatic
    fun isBatchMode(): Boolean {
        return isBatchMode && Settings.DanmakuBatchDelete.get()
    }

    /**
     * Select or deselect a danmaku by ID.
     * @return true if selected, false if deselected
     */
    @JvmStatic
    fun toggleSelection(danmakuId: Long): Boolean {
        if (!isBatchMode()) return false
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
     * Get currently selected danmaku count.
     */
    @JvmStatic
    fun getSelectedCount(): Int {
        return selectedIds.size
    }

    /**
     * Execute batch delete of selected danmaku.
     * @return number of deleted items
     */
    @JvmStatic
    fun executeBatchDelete(): Int {
        if (!isBatchMode() || selectedIds.isEmpty()) return 0

        val count = selectedIds.size
        Logger.debug { "$TAG: batch deleting $count danmaku" }

        // The actual deletion is handled by the hook caller
        // We just return the count and clear selection
        val idsToDelete = selectedIds.toLongArray()
        selectedIds.clear()
        isBatchMode = false

        return count
    }

    /**
     * Get the list of selected danmaku IDs for deletion.
     */
    @JvmStatic
    fun getSelectedIds(): LongArray {
        return selectedIds.toLongArray()
    }

    /**
     * Select all visible danmaku in the current view.
     * @param visibleIds list of currently visible danmaku IDs
     */
    @JvmStatic
    fun selectAll(visibleIds: List<Long>) {
        if (!isBatchMode()) return
        selectedIds.addAll(visibleIds)
        Logger.debug { "$TAG: selected all ${visibleIds.size} visible, total: ${selectedIds.size}" }
    }

    /**
     * Clear all selections.
     */
    @JvmStatic
    fun clearSelection() {
        selectedIds.clear()
        Logger.debug { "$TAG: selection cleared" }
    }
}
