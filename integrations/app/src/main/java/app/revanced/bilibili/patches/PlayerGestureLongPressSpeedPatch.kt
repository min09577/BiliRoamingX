package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerGestureLongPressSpeedPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerGestureLongPressSpeed.get()
}
