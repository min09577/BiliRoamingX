package app.revanced.bilibili.patches

import android.media.AudioManager
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object VolumeBoostPatch {

    @JvmStatic
    fun getMaxVolume(originalMax: Int): Int {
        if (!Settings.VolumeBoost.get()) return originalMax

        val boostLevel = Settings.VolumeBoostLevel.get()
        if (boostLevel <= 100) return originalMax

        val boostedMax = (originalMax * boostLevel / 100).coerceAtMost(150)
        Logger.debug { "VolumeBoost: max volume boosted from $originalMax to $boostedMax" }
        return boostedMax
    }

    @JvmStatic
    fun scaleVolume(volume: Int, maxVolume: Int): Int {
        if (!Settings.VolumeBoost.get()) return volume

        val boostLevel = Settings.VolumeBoostLevel.get()
        if (boostLevel <= 100) return volume

        val scaled = (volume * boostLevel / 100).coerceAtMost(maxVolume)
        return scaled
    }

    @JvmStatic
    fun isBoostEnabled(): Boolean {
        return Settings.VolumeBoost.get() && Settings.VolumeBoostLevel.get() > 100
    }

    @JvmStatic
    fun getBoostLevel(): Int {
        return Settings.VolumeBoostLevel.get()
    }
}
