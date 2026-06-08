package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object HomeFeedCachePatch {
    private const val TAG = "FeedCache"
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedCache.get()
    @JvmStatic fun shouldCache(): Boolean {
        val cache = isEnabled()
        Logger.debug { "$TAG: cache=$cache" }
        return cache
    }
}
