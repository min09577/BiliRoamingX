package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object PlayerGestureDoubleTapPatch {
    private const val TAG = "DoubleTap"
    const val ACTION_DEFAULT = 0
    const val ACTION_SEEK_FORWARD = 1
    const val ACTION_SEEK_BACKWARD = 2
    const val ACTION_TOGGLE_PLAY = 3
    /**
     * getAction
     */
    @JvmStatic fun getAction(): Int = Settings.PlayerGestureDoubleTap.get().coerceIn(0, 3)
    /**
     * isSeekForward
     */
    @JvmStatic fun isSeekForward(): Boolean = getAction() == ACTION_SEEK_FORWARD
    /**
     * isSeekBackward
     */
    @JvmStatic fun isSeekBackward(): Boolean = getAction() == ACTION_SEEK_BACKWARD
    /**
     * isTogglePlay
     */
    @JvmStatic fun isTogglePlay(): Boolean = getAction() == ACTION_TOGGLE_PLAY
}
