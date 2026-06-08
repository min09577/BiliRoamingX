package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 首页快捷方式自定义 — 在首页添加常用功能快捷入口
 * 支持：直播、番剧、游戏中心、漫画、课堂、会员购等
 */
@Keep
object HomeShortcutPatch {
    private const val TAG = "HomeShortcut"

    private val AVAILABLE_SHORTCUTS = listOf(
        "live" to "直播",
        "bangumi" to "番剧",
        "game" to "游戏中心",
        "manga" to "漫画",
        "lesson" to "课堂",
        "vipshop" to "会员购"
    )

    @JvmStatic
    fun getEnabledShortcuts(): Set<String> {
        return Settings.HomeShortcut.get()
    }

    @JvmStatic
    fun isShortcutEnabled(shortcutId: String): Boolean {
        val shortcuts = getEnabledShortcuts()
        return shortcuts.isEmpty() || shortcuts.contains(shortcutId)
    }

    @JvmStatic
    fun getAvailableShortcuts(): List<Pair<String, String>> {
        return AVAILABLE_SHORTCUTS
    }

    @JvmStatic
    fun getShortcutNames(): List<String> {
        return AVAILABLE_SHORTCUTS.map { it.second }
    }
}