package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedUnreadBadgePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedUnreadBadge.get()
}
