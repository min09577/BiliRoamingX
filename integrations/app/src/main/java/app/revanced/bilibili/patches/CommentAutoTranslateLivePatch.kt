package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentAutoTranslateLivePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentAutoTranslateLive.get()
}
