package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentAutoCollapseOldPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentAutoCollapseOld.get()
    @JvmStatic fun shouldCollapse(daysOld: Int): Boolean = isEnabled() && daysOld > 7
}
