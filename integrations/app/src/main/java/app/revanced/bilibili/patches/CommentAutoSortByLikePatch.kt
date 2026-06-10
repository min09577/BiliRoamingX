package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentAutoSortByLikePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentAutoSortByLike.get()
    /**
     * getSortType
     */
    @JvmStatic fun getSortType(): Int = if (isEnabled()) 2 else 0
}
