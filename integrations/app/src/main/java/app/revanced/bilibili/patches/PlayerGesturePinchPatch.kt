package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerGesturePinchPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerGesturePinch.get()
}
