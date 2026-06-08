package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicAutoArchiveAllPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicAutoArchiveAll.get()
}
