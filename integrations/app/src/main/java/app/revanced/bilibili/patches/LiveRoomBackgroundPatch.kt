package app.revanced.bilibili.patches

import android.graphics.Color
import android.view.View
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 直播间背景自定义 — 设置直播间的背景样式
 * 支持：默认、纯黑、纯白、自定义颜色
 */
@Keep
object LiveRoomBackgroundPatch {
    private const val TAG = "LiveRoomBg"
    const val BG_DEFAULT = 0
    const val BG_BLACK = 1
    const val BG_WHITE = 2
    const val BG_CUSTOM = 3

    @JvmStatic
    fun getBackgroundPreset(): Int {
        return Settings.LiveRoomBackground.get().coerceIn(0, 3)
    }

    @JvmStatic
    fun applyBackground(backgroundView: View?) {
        if (backgroundView == null) return
        when (getBackgroundPreset()) {
            BG_BLACK -> {
                backgroundView.setBackgroundColor(Color.BLACK)
                Logger.debug { "$TAG: applied black background" }
            }
            BG_WHITE -> {
                backgroundView.setBackgroundColor(Color.WHITE)
                Logger.debug { "$TAG: applied white background" }
            }
            BG_CUSTOM -> {
                // Custom color would be configured elsewhere
                backgroundView.setBackgroundColor(Color.DKGRAY)
                Logger.debug { "$TAG: applied custom background" }
            }
            else -> {
                Logger.debug { "$TAG: using default background" }
            }
        }
    }

    @JvmStatic
    fun getBackgroundDescription(): String {
        return when (getBackgroundPreset()) {
            BG_BLACK -> "纯黑背景"
            BG_WHITE -> "纯白背景"
            BG_CUSTOM -> "自定义颜色"
            else -> "默认"
        }
    }
}
