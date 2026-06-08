package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentMedalDisplayPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentMedalDisplay.get()
}
