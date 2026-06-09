package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentFrameInterpolationPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentFrameInterpolation.get()
}
