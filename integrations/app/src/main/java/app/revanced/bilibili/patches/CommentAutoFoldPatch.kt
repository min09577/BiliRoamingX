package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentAutoFoldPatch {
    private const val FOLD_THRESHOLD = 200
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentAutoFold.get()
    @JvmStatic fun shouldFold(text: String): Boolean = isEnabled() && text.length > FOLD_THRESHOLD
}
