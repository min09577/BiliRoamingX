package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoSmartCachePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoSmartCache.get()
    @JvmStatic fun getMaxCacheSizeMb(): Int = if (isEnabled()) 500 else 0
}
