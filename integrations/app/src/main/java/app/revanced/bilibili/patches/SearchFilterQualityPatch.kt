package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object SearchFilterQualityPatch {
    private const val TAG = "SearchFilterQuality"
    @JvmStatic fun isEnabled(): Boolean = Settings.SearchFilterQuality.get()
    @JvmStatic fun shouldFilter(viewCount: Long, likeCount: Long): Boolean {
        if (!isEnabled()) return false
        return viewCount < 100 && likeCount < 10
    }
}
