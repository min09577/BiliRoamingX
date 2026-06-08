package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerGestureSwipeSpeedPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerGestureSwipeSpeed.get()
}
