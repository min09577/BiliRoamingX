package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerSplitScreenModePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerSplitScreenMode.get()
}
