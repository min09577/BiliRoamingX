package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicPinTopPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicPinTop.get()
    @JvmStatic fun shouldPinTop(): Boolean = isEnabled()
}
