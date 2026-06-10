package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoAutoSkipPreviewPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoAutoSkipPreview.get()
    /**
     * shouldSkipPreview
     */
    @JvmStatic fun shouldSkipPreview(): Boolean = isEnabled()
}
