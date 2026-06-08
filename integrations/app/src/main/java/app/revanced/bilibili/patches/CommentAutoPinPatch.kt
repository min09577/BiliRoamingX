package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 评论自动置顶 — 发送评论后自动将其置顶显示
 */
@Keep
object CommentAutoPinPatch {
    private const val TAG = "CommentAutoPin"

    @JvmStatic
    fun isEnabled(): Boolean = Settings.CommentAutoPin.get()

    @JvmStatic
    fun shouldAutoPin(): Boolean {
        val pin = isEnabled()
        Logger.debug { "$TAG: auto pin=$pin" }
        return pin
    }
}
