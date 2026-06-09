package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentObjectDetectionPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentObjectDetection.get()
}
