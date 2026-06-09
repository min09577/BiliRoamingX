package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicAutoArchivePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicAutoArchive.get()
    @JvmStatic fun shouldAutoArchive(): Boolean = isEnabled()
}
