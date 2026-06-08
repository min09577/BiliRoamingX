package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerGestureShakeUndoPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerGestureShakeUndo.get()
}
