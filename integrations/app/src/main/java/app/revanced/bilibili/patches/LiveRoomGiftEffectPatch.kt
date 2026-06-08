package app.revanced.bilibili.patches

import android.view.View
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 直播间礼物特效屏蔽 — 隐藏直播间全屏礼物特效动画
 * 减少干扰、节省性能
 */
@Keep
object LiveRoomGiftEffectPatch {
    private const val TAG = "GiftEffect"

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.LiveRoomGiftEffect.get()
    }

    @JvmStatic
    fun shouldBlockGiftEffect(): Boolean {
        val blocked = isEnabled()
        Logger.debug { "$TAG: gift effect blocked=$blocked" }
        return blocked
    }

    @JvmStatic
    fun shouldBlockGiftAnimation(giftType: String): Boolean {
        if (!isEnabled()) return false
        Logger.debug { "$TAG: blocking gift animation type=$giftType" }
        return true
    }

    @JvmStatic
    fun shouldBlockFullScreenEffect(): Boolean {
        return isEnabled()
    }

    @JvmStatic
    fun hideGiftView(view: View?) {
        if (!isEnabled() || view == null) return
        view.visibility = View.GONE
        Logger.debug { "$TAG: hidden gift view" }
    }

    @JvmStatic
    fun shouldBlockComboEffect(): Boolean {
        return isEnabled()
    }
}