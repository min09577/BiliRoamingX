package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicAutoArchiveOldPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicAutoArchiveOld.get()
    private const val ARCHIVE_DAYS = 30
    @JvmStatic fun shouldArchive(daysOld: Int): Boolean = isEnabled() && daysOld > ARCHIVE_DAYS
}
