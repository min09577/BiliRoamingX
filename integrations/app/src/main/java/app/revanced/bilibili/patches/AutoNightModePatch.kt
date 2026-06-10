package app.revanced.bilibili.patches

import android.app.UiModeManager
import android.content.Context
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import java.util.*

@Keep
object AutoNightModePatch {

    private var lastCheckHour = -1

    @JvmStatic
    fun checkAndSwitch(context: Context?) {
        if (!Settings.AutoNightMode.get()) return
        if (context == null) return

        try {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)

            // Only check once per hour
            if (hour == lastCheckHour) return
            lastCheckHour = hour

            // Night mode: 19:00 - 6:00
            val isNightTime = hour >= 19 || hour < 6

            val uiModeManager = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
            val currentMode = uiModeManager.nightMode
            val shouldBeNight = isNightTime

            val currentIsNight = currentMode == UiModeManager.MODE_NIGHT_YES

            if (shouldBeNight != currentIsNight) {
                uiModeManager.nightMode = if (shouldBeNight) {
                    UiModeManager.MODE_NIGHT_YES
                } else {
                    UiModeManager.MODE_NIGHT_NO
                }
                Logger.debug { "AutoNightMode: switched to ${if (shouldBeNight) "night" else "day"} mode" }
            }
        } catch (e: Throwable) {
            Logger.error(e) { "AutoNightMode: failed to switch" }
        }
    }

    @JvmStatic
    fun isNightTime(): Boolean {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return hour >= 19 || hour < 6
    }
}
