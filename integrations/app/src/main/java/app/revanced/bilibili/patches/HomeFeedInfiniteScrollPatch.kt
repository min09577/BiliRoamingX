package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedInfiniteScrollPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedInfiniteScroll.get()
}
