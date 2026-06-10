package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerGestureLongPressPatch {
    const val ACTION_DEFAULT = 0; const val ACTION_SPEED_UP = 1; const val ACTION_SCREENSHOT = 2; const val ACTION_TOGGLE_PLAY = 3
    /**
     * getAction
     */
    @JvmStatic fun getAction(): Int = Settings.PlayerGestureLongPress.get().coerceIn(0, 3)
    /**
     * isSpeedUp
     */
    @JvmStatic fun isSpeedUp(): Boolean = getAction() == ACTION_SPEED_UP
    /**
     * isScreenshot
     */
    @JvmStatic fun isScreenshot(): Boolean = getAction() == ACTION_SCREENSHOT
    /**
     * isTogglePlay
     */
    @JvmStatic fun isTogglePlay(): Boolean = getAction() == ACTION_TOGGLE_PLAY
}
