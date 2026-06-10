package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object VideoCacheSizePatch {

    @JvmStatic
    fun getCacheSizeMB(): Int {
        val size = Settings.VideoCacheSize.get()
        return if (size > 0) size else 0
    }

    @JvmStatic
    fun getCacheSizeBytes(): Long {
        return getCacheSizeMB().toLong() * 1024 * 1024
    }

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.VideoCacheSize.get() > 0
    }
}
