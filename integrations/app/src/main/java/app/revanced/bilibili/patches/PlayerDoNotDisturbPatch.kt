package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerDoNotDisturbPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerDoNotDisturb.get()
}
