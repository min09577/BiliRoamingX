package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerGestureShakePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerGestureShake.get()
}
