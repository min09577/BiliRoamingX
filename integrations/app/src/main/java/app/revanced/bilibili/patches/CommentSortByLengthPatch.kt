package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentSortByLengthPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentSortByLength.get()
    /**
     * compare
     */
    @JvmStatic fun compare(a: String, b: String): Int = b.length - a.length
}
