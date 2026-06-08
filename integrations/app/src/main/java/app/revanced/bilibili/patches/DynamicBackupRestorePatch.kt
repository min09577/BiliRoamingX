package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicBackupRestorePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicBackupRestore.get()
}
