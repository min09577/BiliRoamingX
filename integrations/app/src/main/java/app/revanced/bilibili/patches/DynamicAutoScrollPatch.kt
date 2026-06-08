package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 动态自动滚动 — 动态页面自动滚动加载更多内容
 */
@Keep
object DynamicAutoScrollPatch {
    private const val TAG = "DynamicAutoScroll"

    @JvmStatic
    fun isEnabled(): Boolean = Settings.DynamicAutoScroll.get()

    @JvmStatic
    fun shouldAutoScroll(): Boolean {
        val scroll = isEnabled()
        Logger.debug { "$TAG: auto scroll=$scroll" }
        return scroll
    }
}
