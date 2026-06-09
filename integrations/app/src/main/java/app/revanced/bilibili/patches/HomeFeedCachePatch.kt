package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedCachePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedCache.get()
    @JvmStatic fun getCacheSize(): Long = if (isEnabled()) 200L * 1024 * 1024 else 50L * 1024 * 1024
}
