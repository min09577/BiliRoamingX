package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuDeepLearningFilterPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuDeepLearningFilter.get()
}
