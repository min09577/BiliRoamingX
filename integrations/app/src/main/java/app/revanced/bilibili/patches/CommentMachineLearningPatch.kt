package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentMachineLearningPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentMachineLearning.get()
}
