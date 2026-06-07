package app.revanced.bilibili.patches

import android.graphics.Color
import android.view.View
import android.view.Window
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 播放器背景变暗 — 调节播放器背景的变暗程度
 * 0=不调整, 1-100=变暗百分比，值越大背景越暗
 */
@Keep
object PlayerBackgroundDimPatch {
    private const val TAG = "PlayerBackgroundDim"
    // Default dim levels
    private const val DIM_NONE = 0
    private const val DIM_LIGHT = 25
    private const val DIM_MEDIUM = 50
    private const val DIM_DARK = 75
    private const val DIM_VERY_DARK = 90

    /**
     * Get the configured dim level (0-100).
     */
    @JvmStatic
    fun getDimLevel(): Int {
        return Settings.PlayerBackgroundDim.get().coerceIn(0, 100)
    }

    /**
     * Hook target: apply dim to player background view.
     * Called when player is shown in non-fullscreen mode.
     */
    @JvmStatic
    fun applyDim(backgroundView: View) {
        val level = getDimLevel()
        if (level <= 0) return

        try {
            val alpha = level / 100f
            val dimColor = Color.argb((alpha * 255).toInt(), 0, 0, 0)
            backgroundView.setBackgroundColor(dimColor)
            Logger.debug { "$TAG: applied dim level $level" }
        } catch (e: Throwable) {
            Logger.debug { "$TAG: failed to apply dim: $e" }
        }
    }

    /**
     * Hook target: apply dim to window background.
     * Used for fullscreen player mode.
     */
    @JvmStatic
    fun applyWindowDim(window: Window?) {
        val level = getDimLevel()
        if (level <= 0 || window == null) return

        try {
            val alpha = level / 100f
            val dimColor = Color.argb((alpha * 255).toInt(), 0, 0, 0)
            window.decorView.setBackgroundColor(dimColor)
            Logger.debug { "$TAG: applied window dim level $level" }
        } catch (e: Throwable) {
            Logger.debug { "$TAG: failed to apply window dim: $e" }
        }
    }

    /**
     * Hook target: remove dim (restore transparency).
     * Called when player is closed.
     */
    @JvmStatic
    fun removeDim(backgroundView: View) {
        try {
            backgroundView.setBackgroundColor(Color.TRANSPARENT)
            Logger.debug { "$TAG: dim removed" }
        } catch (e: Throwable) {
            Logger.debug { "$TAG: failed to remove dim: $e" }
        }
    }

    /**
     * Get dim level description for UI display.
     */
    @JvmStatic
    fun getDimDescription(): String {
        val level = getDimLevel()
        return when {
            level <= 0 -> "不调整"
            level <= DIM_LIGHT -> "轻微变暗 ($level%)"
            level <= DIM_MEDIUM -> "中等变暗 ($level%)"
            level <= DIM_DARK -> "较暗 ($level%)"
            else -> "非常暗 ($level%)"
        }
    }
}
