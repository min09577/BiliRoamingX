package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicAutoRepostPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicAutoRepost.get()
}
