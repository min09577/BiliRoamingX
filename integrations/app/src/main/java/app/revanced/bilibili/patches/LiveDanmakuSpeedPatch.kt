package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object LiveDanmakuSpeedPatch {
    private const val TAG = "LiveDanmakuSpeed"
    const val SPEED_SLOW = 0
    const val SPEED_NORMAL = 1
    const val SPEED_FAST = 2
    /**
     * getSpeedPreset
     */
    @JvmStatic fun getSpeedPreset(): Int = Settings.LiveDanmakuSpeed.get().coerceIn(0, 2)
    /**
     * getSpeedFactor
     */
    @JvmStatic fun getSpeedFactor(): Float = when (getSpeedPreset()) {
        SPEED_SLOW -> 0.5f; SPEED_FAST -> 2.0f; else -> 1.0f
    }
}
