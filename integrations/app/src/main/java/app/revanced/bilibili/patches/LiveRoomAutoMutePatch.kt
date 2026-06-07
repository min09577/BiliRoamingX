package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 直播间自动静音 — 进入直播间时自动静音
 * 避免突然的直播声音打扰，需要手动取消静音
 */
@Keep
object LiveRoomAutoMutePatch {
    private const val TAG = "LiveRoomAutoMute"

    /**
     * Check if auto-mute is enabled.
     */
    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.LiveRoomAutoMute.get()
    }

    /**
     * Hook target: check if should auto-mute on entering live room.
     * Called when live room is initialized.
     * @return true if should mute
     */
    @JvmStatic
    fun shouldAutoMute(): Boolean {
        val enabled = Settings.LiveRoomAutoMute.get()
        if (enabled) {
            Logger.debug { "$TAG: auto-muting live room" }
        }
        return enabled
    }

    /**
     * Hook target: set volume to 0 for auto-mute.
     * Called when live room audio is initialized.
     * @param originalVolume the original volume level
     * @return volume to set (0 if auto-mute, original otherwise)
     */
    @JvmStatic
    fun getMutedVolume(originalVolume: Int): Int {
        if (!Settings.LiveRoomAutoMute.get()) return originalVolume
        Logger.debug { "$TAG: muting volume $originalVolume -> 0" }
        return 0
    }

    /**
     * Hook target: check if should show unmute button.
     * Called when showing live room controls.
     */
    @JvmStatic
    fun shouldShowUnmuteHint(): Boolean {
        return Settings.LiveRoomAutoMute.get()
    }
}
