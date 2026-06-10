package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerGestureTripleTapPatch {
    const val ACTION_DEFAULT = 0; const val ACTION_SPEED_3X = 1; const val ACTION_REPLAY = 2; const val ACTION_NEXT = 3
    /**
     * getAction
     */
    @JvmStatic fun getAction(): Int = Settings.PlayerGestureTripleTap.get().coerceIn(0, 3)
    /**
     * isSpeed3x
     */
    @JvmStatic fun isSpeed3x(): Boolean = getAction() == ACTION_SPEED_3X
    /**
     * isReplay
     */
    @JvmStatic fun isReplay(): Boolean = getAction() == ACTION_REPLAY
    /**
     * isNext
     */
    @JvmStatic fun isNext(): Boolean = getAction() == ACTION_NEXT
}
