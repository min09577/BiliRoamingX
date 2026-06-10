package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedAutoScrollPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedAutoScroll.get()
    /**
     * shouldAutoScroll
     */
    @JvmStatic fun shouldAutoScroll(): Boolean = isEnabled()
}
