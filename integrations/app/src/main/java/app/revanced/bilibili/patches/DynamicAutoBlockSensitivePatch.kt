package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicAutoBlockSensitivePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicAutoBlockSensitive.get()
    @JvmStatic fun shouldBlock(): Boolean = isEnabled()
}
