package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicAutoPinCommentPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicAutoPinComment.get()
}
