package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object VideoLoopCountPatch {

    private var currentCount = 0

    @JvmStatic
    fun onVideoEnd(): Boolean {
        val maxCount = Settings.VideoLoopCount.get()
        if (maxCount <= 0) return true // Unlimited

        currentCount++
        if (currentCount >= maxCount) {
            Logger.debug { "VideoLoopCount: reached max count $maxCount" }
            currentCount = 0
            return false // Stop looping
        }

        Logger.debug { "VideoLoopCount: loop $currentCount/$maxCount" }
        return true
    }

    @JvmStatic
    fun reset() {
        currentCount = 0
    }

    @JvmStatic
    fun getMaxCount(): Int {
        return Settings.VideoLoopCount.get()
    }

    @JvmStatic
    fun getCurrentCount(): Int {
        return currentCount
    }
}
