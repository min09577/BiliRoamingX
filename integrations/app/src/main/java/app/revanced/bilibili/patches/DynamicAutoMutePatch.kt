package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicAutoMutePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicAutoMute.get()
    @JvmStatic fun shouldAutoMute(): Boolean = isEnabled()
}
