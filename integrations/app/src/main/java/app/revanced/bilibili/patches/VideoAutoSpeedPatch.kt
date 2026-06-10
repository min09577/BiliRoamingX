package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object VideoAutoSpeedPatch {
    private const val TAG = "VideoAutoSpeed"
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoAutoSpeed.get()
    @JvmStatic fun getRecommendedSpeed(contentType: String): Float {
        if (!isEnabled()) return 1.0f
        return when (contentType) {
            "live" -> 1.0f
            "music" -> 1.0f
            "education" -> 1.25f
            "entertainment" -> 1.5f
            else -> 1.0f
        }
    }
}
