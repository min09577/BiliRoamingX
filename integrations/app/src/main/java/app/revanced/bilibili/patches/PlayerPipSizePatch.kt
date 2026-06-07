package app.revanced.bilibili.patches

import android.graphics.Rect
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 画中画窗口大小调节 — 自定义画中画窗口的大小
 * 支持：小（默认）、中、大三种尺寸
 */
@Keep
object PlayerPipSizePatch {
    private const val TAG = "PipSize"
    // Size presets (width x height in dp)
    val SIZE_SMALL = intArrayOf(200, 120)
    val SIZE_MEDIUM = intArrayOf(280, 160)
    val SIZE_LARGE = intArrayOf(360, 200)

    @JvmStatic
    fun getSizePreset(): Int {
        return Settings.PlayerPipSize.get().coerceIn(0, 2)
    }

    /**
     * Hook target: get PiP dimensions based on setting.
     * @return [width, height] in dp
     */
    @JvmStatic
    fun getPipDimensions(): IntArray {
        return when (getSizePreset()) {
            1 -> SIZE_MEDIUM
            2 -> SIZE_LARGE
            else -> SIZE_SMALL
        }
    }

    /**
     * Hook target: apply size to PiP window bounds.
     * @param bounds the current PiP bounds rect
     * @return modified bounds rect
     */
    @JvmStatic
    fun adjustPipBounds(bounds: Rect): Rect {
        val dims = getPipDimensions()
        val result = Rect(bounds)
        result.right = result.left + dims[0]
        result.bottom = result.top + dims[1]
        Logger.debug { "$TAG: adjusted PiP to ${dims[0]}x${dims[1]}" }
        return result
    }

    /**
     * Get size description for UI.
     */
    @JvmStatic
    fun getSizeDescription(): String {
        return when (getSizePreset()) {
            1 -> "中等"
            2 -> "大"
            else -> "小（默认）"
        }
    }
}
