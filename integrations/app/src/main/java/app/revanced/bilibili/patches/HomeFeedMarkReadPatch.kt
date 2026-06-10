package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedMarkReadPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedMarkRead.get()
    /**
     * shouldMarkRead
     */
    @JvmStatic fun shouldMarkRead(): Boolean = isEnabled()
}
