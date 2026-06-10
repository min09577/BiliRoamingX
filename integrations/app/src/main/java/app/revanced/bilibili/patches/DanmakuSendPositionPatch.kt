package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object DanmakuSendPositionPatch {

    const val POSITION_SCROLL = 0  // 滚动弹幕
    const val POSITION_TOP = 1     // 顶部弹幕
    const val POSITION_BOTTOM = 2  // 底部弹幕

    @JvmStatic
    fun getSendPosition(): Int {
        return Settings.DanmakuSendPosition.get()
    }

    @JvmStatic
    fun getPositionName(): String {
        return when (getSendPosition()) {
            POSITION_TOP -> "顶部"
            POSITION_BOTTOM -> "底部"
            else -> "滚动"
        }
    }

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.DanmakuSendPosition.get() != 0
    }
}
