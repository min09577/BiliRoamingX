package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentONNXInferencePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentONNXInference.get()
}
