package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 直播弹幕颜色自定义 — 设置直播间发送弹幕的默认颜色
 * 0=默认白色, 1=红色, 2=橙色, 3=黄色, 4=绿色, 5=蓝色, 6=紫色
 */
@Keep
object LiveDanmakuColorPatch {
    private const val TAG = "LiveDanmakuColor"
    const val COLOR_DEFAULT = 0
    const val COLOR_RED = 1
    const val COLOR_ORANGE = 2
    const val COLOR_YELLOW = 3
    const val COLOR_GREEN = 4
    const val COLOR_BLUE = 5
    const val COLOR_PURPLE = 6

    private val COLOR_NAMES = arrayOf("默认", "红色", "橙色", "黄色", "绿色", "蓝色", "紫色")
    private val COLOR_HEX_VALUES = intArrayOf(0xFFFFFF, 0xFF0000, 0xFF8C00, 0xFFFF00, 0x00FF00, 0x00BFFF, 0x9B59B6)

    @JvmStatic
    fun getColorPreset(): Int {
        return Settings.LiveDanmakuColor.get().coerceIn(0, 6)
    }

    @JvmStatic
    fun getColorHex(): Int {
        return COLOR_HEX_VALUES[getColorPreset()]
    }

    @JvmStatic
    fun getColorName(): String {
        return COLOR_NAMES[getColorPreset()]
    }

    @JvmStatic
    fun applyColor(danmakuColor: MutableMap<String, Any>) {
        if (getColorPreset() == COLOR_DEFAULT) return
        danmakuColor["color"] = getColorHex()
        Logger.debug { "$TAG: applied color=${getColorName()}, hex=${getColorHex()}" }
    }

    @JvmStatic
    fun isCustomColor(): Boolean {
        return getColorPreset() != COLOR_DEFAULT
    }
}