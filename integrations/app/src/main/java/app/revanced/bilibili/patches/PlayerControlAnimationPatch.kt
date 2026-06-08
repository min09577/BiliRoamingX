package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerControlAnimationPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerControlAnimation.get()
}
