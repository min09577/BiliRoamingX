package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object DanmakuSpeedSyncPatch {

    @JvmStatic
    fun getAdjustedSpeed(baseSpeed: Float, playbackSpeed: Float): Float {
        if (!Settings.DanmakuSpeedSync.get()) return baseSpeed

        // When playback is faster, danmaku should also be faster
        // When playback is slower, danmaku should also be slower
        val adjusted = baseSpeed / playbackSpeed
        Logger.debug { "DanmakuSpeedSync: base=$baseSpeed playback=$playbackSpeed adjusted=$adjusted" }
        return adjusted
    }

    @JvmStatic
    fun shouldSync(): Boolean {
        return Settings.DanmakuSpeedSync.get()
    }
}
