package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerDolbyAtmosPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerDolbyAtmos.get()
}
