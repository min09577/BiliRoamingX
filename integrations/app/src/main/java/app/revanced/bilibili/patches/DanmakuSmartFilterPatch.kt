package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

/**
 * 智能弹幕过滤 — 自动过滤低质量弹幕（纯数字、重复内容、过短弹幕）
 */
@Keep
object DanmakuSmartFilterPatch {
    private const val MIN_DANMAKU_LENGTH = 2
    private const val TAG = "SmartFilter"
    private const val MIN_LENGTH = 2

    @JvmStatic
    fun isEnabled(): Boolean = Settings.DanmakuSmartFilter.get()

    @JvmStatic
    fun shouldFilter(text: String): Boolean {
        if (!isEnabled()) return false
        if (text.length < MIN_LENGTH) return true
        if (text.matches(Regex("^\\d+$"))) return true
        if (text.matches(Regex("^(.)\\1{2,}$"))) return true
        return false
    }
}
