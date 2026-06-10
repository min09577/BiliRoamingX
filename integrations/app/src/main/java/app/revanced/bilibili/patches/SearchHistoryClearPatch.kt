package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object SearchHistoryClearPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.SearchHistoryClear.get()
    /**
     * shouldAutoClear
     */
    @JvmStatic fun shouldAutoClear(): Boolean = isEnabled()
}
