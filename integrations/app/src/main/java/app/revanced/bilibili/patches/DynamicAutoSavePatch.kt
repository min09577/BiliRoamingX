package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicAutoSavePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicAutoSave.get()
}
