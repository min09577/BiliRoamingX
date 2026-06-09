package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerMandelbrotEffectPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerMandelbrotEffect.get()
}
