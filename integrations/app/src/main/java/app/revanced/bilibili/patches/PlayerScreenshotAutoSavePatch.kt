package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerScreenshotAutoSavePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerScreenshotAutoSave.get()
    /**
     * shouldAutoSave
     */
    @JvmStatic fun shouldAutoSave(): Boolean = isEnabled()
}
