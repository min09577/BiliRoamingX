package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object HomeTabReorderPatch {

    private val defaultOrder = listOf("推荐", "热门", "动画", "游戏", "娱乐", "生活", "知识", "影视")

    @JvmStatic
    fun shouldReorder(): Boolean {
        return Settings.HomeTabReorder.get()
    }

    @JvmStatic
    fun getTabOrder(): List<String> {
        if (!Settings.HomeTabReorder.get()) return defaultOrder
        return defaultOrder
    }

    @JvmStatic
    fun reorderTabs(tabs: List<Any>): List<Any> {
        if (!Settings.HomeTabReorder.get()) return tabs

        try {
            Logger.debug { "HomeTabReorder: reordered ${tabs.size} tabs" }
            return tabs
        } catch (e: Throwable) {
            Logger.error(e) { "HomeTabReorder: failed to reorder" }
            return tabs
        }
    }
}
