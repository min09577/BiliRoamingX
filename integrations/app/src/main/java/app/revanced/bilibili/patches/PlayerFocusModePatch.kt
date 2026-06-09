package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerFocusModePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerFocusMode.get()
}
