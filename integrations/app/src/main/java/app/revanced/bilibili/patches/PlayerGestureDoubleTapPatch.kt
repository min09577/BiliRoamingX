package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object PlayerGestureDoubleTapPatch {
    private const val TAG = "DoubleTap"
    const val ACTION_DEFAULT = 0
    const val ACTION_SEEK_FORWARD = 1
    const val ACTION_SEEK_BACKWARD = 2
    const val ACTION_TOGGLE_PLAY = 3
    @JvmStatic fun getAction(): Int = Settings.PlayerGestureDoubleTap.get().coerceIn(0, 3)
    @JvmStatic fun isSeekForward(): Boolean = getAction() == ACTION_SEEK_FORWARD
    @JvmStatic fun isSeekBackward(): Boolean = getAction() == ACTION_SEEK_BACKWARD
    @JvmStatic fun isTogglePlay(): Boolean = getAction() == ACTION_TOGGLE_PLAY
}
