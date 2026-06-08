package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 按用户ID屏蔽弹幕 — 屏蔽指定用户发送的弹幕
 * 用户ID列表在设置中配置
 */
@Keep
object DanmakuBlockByUserPatch {
    private const val TAG = "DanmakuBlockByUser"

    @JvmStatic
    fun getBlockedUsers(): Set<String> {
        return Settings.DanmakuBlockByUser.get()
    }

    @JvmStatic
    fun isBlocked(userId: String): Boolean {
        val blocked = getBlockedUsers().contains(userId)
        if (blocked) Logger.debug { "$TAG: blocked danmaku from user $userId" }
        return blocked
    }

    @JvmStatic
    fun shouldBlock(userId: String): Boolean {
        return getBlockedUsers().contains(userId)
    }
}