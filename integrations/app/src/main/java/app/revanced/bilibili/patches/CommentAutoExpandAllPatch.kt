package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentAutoExpandAllPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentAutoExpandAll.get()
}
