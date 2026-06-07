package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object VideoSpeedPresetsPatch {

    private val PRESET_SPEEDS = floatArrayOf(0.5f, 0.75f, 1.0f, 1.25f, 1.5f, 2.0f, 3.0f)

    @JvmStatic
    fun getPresets(): FloatArray {
        if (!Settings.VideoSpeedPresets.get()) return floatArrayOf()
        return PRESET_SPEEDS
    }

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.VideoSpeedPresets.get()
    }

    @JvmStatic
    fun formatSpeed(speed: Float): String {
        return if (speed == speed.toLong().toFloat()) {
            "${speed.toLong()}x"
        } else {
            "${speed}x"
        }
    }
}
