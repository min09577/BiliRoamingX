package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeWidgetCustomPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeWidgetCustom.get()
    /**
     * shouldCustomize
     */
    @JvmStatic fun shouldCustomize(): Boolean = isEnabled()
}
