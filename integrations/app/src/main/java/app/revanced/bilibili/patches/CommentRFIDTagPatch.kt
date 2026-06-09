package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentRFIDTagPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentRFIDTag.get()
}
