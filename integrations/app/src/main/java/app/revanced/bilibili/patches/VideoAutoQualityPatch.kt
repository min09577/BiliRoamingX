package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object VideoAutoQualityPatch {
    private const val TAG = "AutoQuality"
    const val QUALITY_AUTO = 0
    const val QUALITY_720P = 1
    const val QUALITY_1080P = 2
    const val QUALITY_4K = 3
    /**
     * getQualityPreset
     */
    @JvmStatic fun getQualityPreset(): Int = Settings.VideoAutoQuality.get().coerceIn(0, 3)
    /**
     * getRecommendedQuality
     */
    @JvmStatic fun getRecommendedQuality(bandwidth: Int): Int = when {
        bandwidth >= 10_000 -> 127  // 4K
        bandwidth >= 5_000 -> 80    // 1080P
        bandwidth >= 2_000 -> 64    // 720P
        else -> 32                   // 480P
    }
    /**
     * getQualityDescription
     */
    @JvmStatic fun getQualityDescription(): String = when (getQualityPreset()) {
        QUALITY_720P -> "720P"; QUALITY_1080P -> "1080P"; QUALITY_4K -> "4K"; else -> "自动"
    }
}
