package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentAutoPinTopPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentAutoPinTop.get()
}
