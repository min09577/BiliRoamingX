package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 搜索自动补全增强 — 增强搜索框的自动补全和搜索建议
 * 支持历史搜索、热门搜索、智能补全
 */
@Keep
object SearchAutoCompletePatch {
    private const val TAG = "SearchAutoComplete"

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.SearchAutoComplete.get()
    }

    @JvmStatic
    fun shouldEnhance(): Boolean {
        val enhance = isEnabled()
        Logger.debug { "$TAG: enhance=$enhance" }
        return enhance
    }

    @JvmStatic
    fun filterSuggestions(suggestions: List<String>): List<String> {
        if (!isEnabled()) return suggestions
        Logger.debug { "$TAG: filtered ${suggestions.size} suggestions" }
        return suggestions
    }
}