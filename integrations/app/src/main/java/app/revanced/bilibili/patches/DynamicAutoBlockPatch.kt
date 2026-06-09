package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicAutoBlockPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicAutoBlock.get()
    @JvmStatic fun shouldAutoBlock(): Boolean = isEnabled()
}
