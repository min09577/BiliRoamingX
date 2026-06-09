package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerChromecastSupportPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerChromecastSupport.get()
}
