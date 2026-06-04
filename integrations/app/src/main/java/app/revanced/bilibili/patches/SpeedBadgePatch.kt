package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.PlayerHookProvider.showTextToast
import app.revanced.bilibili.utils.Utils

@Keep
object SpeedBadgePatch {
    private var lastSpeed = 0f
    private var lastShowTime = 0L
    private const val DEBOUNCE_MS = 500L

    /**
     * Called when playback speed changes.
     * Shows a toast on the player with the current speed.
     */
    @JvmStatic
    fun onSpeedChanged(player: Any?, speed: Float) {
        if (!Settings.SpeedBadge.get()) return

        // Debounce to avoid rapid updates
        val now = System.currentTimeMillis()
        if (speed == lastSpeed && now - lastShowTime < DEBOUNCE_MS) return
        lastSpeed = speed
        lastShowTime = now

        try {
            val speedText = if (speed == speed.toLong().toFloat()) {
                "${speed.toLong()}x"
            } else {
                "${speed}x"
            }

            // Use player's built-in toast system
            player?.showTextToast("⚡ $speedText", center = true, duration = 800L)
            Logger.debug { "SpeedBadge: showing $speedText" }
        } catch (e: Throwable) {
            Logger.error(e) { "SpeedBadge: failed to show speed badge" }
        }
    }

    /**
     * Called when long press speed starts.
     */
    @JvmStatic
    fun onLongPressSpeedStart(player: Any?, speed: Float) {
        if (!Settings.SpeedBadge.get()) return
        try {
            player?.showTextToast("⚡ 长按 ${speed}x", center = true, duration = 800L)
        } catch (e: Throwable) {
            Logger.error(e) { "SpeedBadge: failed to show long press speed" }
        }
    }

    /**
     * Called when long press speed ends.
     */
    @JvmStatic
    fun onLongPressSpeedEnd(player: Any?) {
        if (!Settings.SpeedBadge.get()) return
        try {
            player?.showTextToast("⚡ 恢复", center = true, duration = 500L)
        } catch (e: Throwable) {
            Logger.error(e) { "SpeedBadge: failed to show restore" }
        }
    }
}
