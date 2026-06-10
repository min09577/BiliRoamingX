package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicAutoBookmarkPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicAutoBookmark.get()
    /**
     * shouldBookmark
     */
    @JvmStatic fun shouldBookmark(): Boolean = isEnabled()
}
