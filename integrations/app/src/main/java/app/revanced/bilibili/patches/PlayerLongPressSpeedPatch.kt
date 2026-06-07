package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object PlayerLongPressSpeedPatch {

    @JvmStatic
    fun getLongPressSpeed(): Float {
        val speed = Settings.PlayerLongPressSpeed.get()
        return if (speed > 0) speed else 3.0f
    }

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.PlayerLongPressSpeed.get() > 0
    }
}
