package app.revanced.bilibili.patches

import android.view.View
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 播放器控制栏布局自定义 — 调整播放器控制按钮的排列和显示
 * 支持：默认布局、紧凑布局、精简布局
 */
@Keep
object PlayerControlLayoutPatch {
    private const val TAG = "ControlLayout"
    const val LAYOUT_DEFAULT = 0
    const val LAYOUT_COMPACT = 1
    const val LAYOUT_MINIMAL = 2

    @JvmStatic
    fun getLayoutPreset(): Int {
        return Settings.PlayerControlLayout.get().coerceIn(0, 2)
    }

    @JvmStatic
    fun isDefaultLayout(): Boolean = getLayoutPreset() == LAYOUT_DEFAULT
    @JvmStatic
    fun isCompactLayout(): Boolean = getLayoutPreset() == LAYOUT_COMPACT
    @JvmStatic
    fun isMinimalLayout(): Boolean = getLayoutPreset() == LAYOUT_MINIMAL

    @JvmStatic
    fun applyLayout(controlBar: View?) {
        if (controlBar == null) return
        when (getLayoutPreset()) {
            LAYOUT_COMPACT -> {
                controlBar.setPadding(0, 0, 0, 0)
                Logger.debug { "$TAG: applied compact layout" }
            }
            LAYOUT_MINIMAL -> {
                controlBar.visibility = View.GONE
                Logger.debug { "$TAG: applied minimal layout (hidden)" }
            }
            else -> {
                Logger.debug { "$TAG: using default layout" }
            }
        }
    }

    @JvmStatic
    fun shouldShowButton(buttonName: String): Boolean {
        if (isMinimalLayout()) return false
        return true
    }

    @JvmStatic
    fun getLayoutDescription(): String {
        return when (getLayoutPreset()) {
            LAYOUT_COMPACT -> "紧凑布局"
            LAYOUT_MINIMAL -> "精简布局"
            else -> "默认布局"
        }
    }
}
