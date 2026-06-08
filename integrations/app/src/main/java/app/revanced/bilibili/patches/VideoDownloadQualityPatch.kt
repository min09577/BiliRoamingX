package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 下载画质选择 — 自定义视频下载的画质
 * 0=默认, 1=流畅360P, 2=清晰480P, 3=高清720P, 4=超清1080P, 5=蓝光4K
 */
@Keep
object VideoDownloadQualityPatch {
    private const val TAG = "DownloadQuality"
    const val QUALITY_DEFAULT = 0
    const val QUALITY_360P = 1
    const val QUALITY_480P = 2
    const val QUALITY_720P = 3
    const val QUALITY_1080P = 4
    const val QUALITY_4K = 5

    private val QUALITY_LABELS = arrayOf("默认", "流畅360P", "清晰480P", "高清720P", "超清1080P", "蓝光4K")
    private val QUALITY_VALUES = intArrayOf(0, 16, 32, 64, 80, 127)

    @JvmStatic
    fun getQualityPreset(): Int {
        return Settings.VideoDownloadQuality.get().coerceIn(0, 5)
    }

    @JvmStatic
    fun getQualityValue(): Int {
        return QUALITY_VALUES[getQualityPreset()]
    }

    @JvmStatic
    fun getQualityLabel(): String {
        return QUALITY_LABELS[getQualityPreset()]
    }

    @JvmStatic
    fun applyDownloadQuality(qualityParam: MutableMap<String, String>) {
        if (getQualityPreset() == QUALITY_DEFAULT) return
        qualityParam["qn"] = getQualityValue().toString()
        Logger.debug { "$TAG: applied quality=${getQualityLabel()}, qn=${getQualityValue()}" }
    }
}