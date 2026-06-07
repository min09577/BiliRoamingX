package app.revanced.bilibili.patches

import android.content.Context
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object PlayerBrightnessMemoryPatch {

    private const val PREFS_NAME = "brightness_memory"

    @JvmStatic
    fun saveBrightness(context: Context?, videoId: String?, brightness: Float) {
        if (!Settings.PlayerBrightnessMemory.get()) return
        if (context == null || videoId == null) return

        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putFloat(videoId, brightness).apply()
            Logger.debug { "BrightnessMemory: saved $brightness for $videoId" }
        } catch (e: Throwable) {
            Logger.error(e) { "BrightnessMemory: failed to save" }
        }
    }

    @JvmStatic
    fun loadBrightness(context: Context?, videoId: String?): Float {
        if (!Settings.PlayerBrightnessMemory.get()) return -1f
        if (context == null || videoId == null) return -1f

        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val brightness = prefs.getFloat(videoId, -1f)
            if (brightness >= 0) {
                Logger.debug { "BrightnessMemory: loaded $brightness for $videoId" }
            }
            return brightness
        } catch (e: Throwable) {
            Logger.error(e) { "BrightnessMemory: failed to load" }
            return -1f
        }
    }

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.PlayerBrightnessMemory.get()
    }
}
