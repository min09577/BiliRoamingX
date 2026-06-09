package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicAutoDeletePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicAutoDelete.get()
    @JvmStatic fun shouldAutoDelete(): Boolean = isEnabled()
}
