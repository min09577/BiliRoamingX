package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedAIEnhancePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedAIEnhance.get()
}
