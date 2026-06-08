package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedMemoryCachePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedMemoryCache.get()
}
