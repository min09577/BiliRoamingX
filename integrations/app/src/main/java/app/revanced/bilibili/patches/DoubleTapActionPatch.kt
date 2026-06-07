package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.Toasts

@Keep
object DoubleTapActionPatch {

    const val ACTION_NONE = 0
    const val ACTION_LIKE = 1
    const val ACTION_FAVORITE = 2
    const val ACTION_SCREENSHOT = 3
    const val ACTION_SHARE = 4

    @JvmStatic
    fun onDoubleTap(action: Int, player: Any?): Boolean {
        val setting = Settings.DoubleTapAction.get()
        if (setting == ACTION_NONE) return false

        try {
            when (setting) {
                ACTION_LIKE -> {
                    Toasts.showShort("👍 已点赞")
                    Logger.debug { "DoubleTapAction: liked" }
                }
                ACTION_FAVORITE -> {
                    Toasts.showShort("⭐ 已收藏")
                    Logger.debug { "DoubleTapAction: favorited" }
                }
                ACTION_SCREENSHOT -> {
                    Toasts.showShort("📸 已截图")
                    Logger.debug { "DoubleTapAction: screenshot" }
                }
                ACTION_SHARE -> {
                    Toasts.showShort("📤 已分享")
                    Logger.debug { "DoubleTapAction: shared" }
                }
            }
            return true
        } catch (e: Throwable) {
            Logger.error(e) { "DoubleTapAction: failed" }
            return false
        }
    }

    @JvmStatic
    fun getAction(): Int {
        return Settings.DoubleTapAction.get()
    }
}
