package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 自动跳过花絮/预告 — 自动跳过正片前后的花絮、预告、广告
 * 与AutoSkipIntro/Outro不同，这个跳过的是非正片内容
 */
@Keep
object AutoSkipFillerPatch {
    private const val TAG = "AutoSkipFiller"
    // Filler types
    const val FILLER_NEXT_EP = 1
    const val FILLER_PREVIEW = 2
    const val FILLER_CREDIT = 4
    const val FILLER_AD = 8

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.AutoSkipFiller.get()
    }

    @JvmStatic
    fun shouldSkip(fillerType: Int): Boolean {
        if (!isEnabled()) return false
        Logger.debug { "$TAG: checking filler type=$fillerType" }
        return true
    }

    @JvmStatic
    fun shouldSkipNextEpisode(): Boolean {
        if (!isEnabled()) return false
        Logger.debug { "$TAG: skipping next episode preview" }
        return true
    }

    @JvmStatic
    fun shouldSkipPreview(): Boolean {
        return isEnabled()
    }

    @JvmStatic
    fun shouldSkipCredits(): Boolean {
        return isEnabled()
    }

    @JvmStatic
    fun shouldSkipAd(): Boolean {
        return isEnabled()
    }

    @JvmStatic
    fun getSkipHint(): String {
        return if (isEnabled()) "跳过花絮" else ""
    }
}
