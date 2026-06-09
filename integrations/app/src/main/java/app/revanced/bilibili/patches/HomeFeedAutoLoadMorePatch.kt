package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedAutoLoadMorePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedAutoLoadMore.get()
    @JvmStatic fun shouldAutoLoadMore(): Boolean = isEnabled()
}
