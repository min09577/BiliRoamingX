package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicAutoForwardPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicAutoForward.get()
}
