package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 动态页面自动刷新 — 进入动态页面时自动刷新内容
 * 与HomeDisableAutoRefresh互补
 */
@Keep
object DynamicAutoRefreshPatch {
    private const val TAG = "DynamicAutoRefresh"

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.DynamicAutoRefresh.get()
    }

    @JvmStatic
    fun shouldAutoRefresh(): Boolean {
        val refresh = isEnabled()
        Logger.debug { "$TAG: auto refresh=$refresh" }
        return refresh
    }

    @JvmStatic
    fun onDynamicPageOpened() {
        if (!isEnabled()) return
        Logger.debug { "$TAG: dynamic page opened, triggering refresh" }
    }
}