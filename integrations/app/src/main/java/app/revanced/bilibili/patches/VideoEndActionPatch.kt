package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

/**
 * 视频结束后动作选择 — 自定义视频播放结束时的行为
 * 0=默认(显示推荐), 1=自动重播, 2=退出播放, 3=播放下一集
 */
@Keep
object VideoEndActionPatch {
    private const val TAG = "VideoEndAction"
    const val ACTION_DEFAULT = 0
    const val ACTION_REPLAY = 1
    const val ACTION_EXIT = 2
    const val ACTION_NEXT = 3

    @JvmStatic
    fun getEndAction(): Int {
        return Settings.VideoEndAction.get().coerceIn(0, 3)
    }

    @JvmStatic
    fun shouldReplay(): Boolean {
        return getEndAction() == ACTION_REPLAY
    }

    @JvmStatic
    fun shouldExit(): Boolean {
        return getEndAction() == ACTION_EXIT
    }

    @JvmStatic
    fun shouldPlayNext(): Boolean {
        return getEndAction() == ACTION_NEXT
    }

    @JvmStatic
    fun getActionDescription(): String {
        return when (getEndAction()) {
            ACTION_REPLAY -> "自动重播"
            ACTION_EXIT -> "退出播放"
            ACTION_NEXT -> "播放下一集"
            else -> "默认"
        }
    }
}