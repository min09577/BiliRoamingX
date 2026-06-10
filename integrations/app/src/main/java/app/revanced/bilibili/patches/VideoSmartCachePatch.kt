package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoSmartCachePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoSmartCache.get()
    /**
     * getCacheSize
     */
    @JvmStatic fun getCacheSize(): Long = if (isEnabled()) 100L * 1024 * 1024 else 50L * 1024 * 1024
}
