package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentAutoDeletePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentAutoDelete.get()
    private const val EXPIRE_DAYS = 30
    @JvmStatic fun shouldDelete(daysOld: Int): Boolean = isEnabled() && daysOld > EXPIRE_DAYS
}
