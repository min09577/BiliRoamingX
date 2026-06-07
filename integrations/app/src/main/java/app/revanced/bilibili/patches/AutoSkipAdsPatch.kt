package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object AutoSkipAdsPatch {

    private val AD_KEYWORDS = listOf(
        "广告", "推广", "赞助", "商务合作",
        "ad", "sponsor", "promoted", "commercial"
    )

    @JvmStatic
    fun shouldSkipAd(content: String?, duration: Long, currentTime: Long): Boolean {
        if (!Settings.AutoSkipAds.get()) return false
        if (content.isNullOrEmpty()) return false

        try {
            val isAd = AD_KEYWORDS.any { content.contains(it, ignoreCase = true) }
            if (isAd) {
                Logger.debug { "AutoSkipAds: detected ad segment, skipping" }
                return true
            }
        } catch (e: Throwable) {
            Logger.error(e) { "AutoSkipAds: check failed" }
        }
        return false
    }

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.AutoSkipAds.get()
    }
}
