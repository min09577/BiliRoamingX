package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.PlayerHookProvider.callMethodFromHook
import app.revanced.bilibili.utils.PlayerHookProvider.seekTo
import app.revanced.bilibili.utils.PlayerHookProvider.showTextToast
import kotlin.math.max
import kotlin.math.min

@Keep
object DoubleTapSeekPatch {

    @JvmStatic
    fun onDoubleTapLeft(player: Any?): Boolean {
        val seekTime = Settings.DoubleTapSeekTime.get()
        if (seekTime <= 0) return false

        try {
            val position = player?.callMethodFromHook("getCurrentPosition", 0)
            val currentPos = if (position is Long) position else if (position is Int) position.toLong() else return false
            val newPosition = maxOf(0, currentPos - seekTime * 1000L)
            player?.seekTo(newPosition.toInt())
            player?.showTextToast("⏪ ${seekTime}s", center = true, duration = 800L)
            Logger.debug { "DoubleTapSeekPatch: seek backward ${seekTime}s" }
            return true
        } catch (e: Throwable) {
            Logger.error(e) { "DoubleTapSeekPatch: failed to seek backward" }
            return false
        }
    }

    @JvmStatic
    fun onDoubleTapRight(player: Any?): Boolean {
        val seekTime = Settings.DoubleTapSeekTime.get()
        if (seekTime <= 0) return false

        try {
            val position = player?.callMethodFromHook("getCurrentPosition", 0)
            val currentPos = if (position is Long) position else if (position is Int) position.toLong() else return false
            val duration = player?.callMethodFromHook("getDuration", 0)
            val dur = if (duration is Long) duration else if (duration is Int) duration.toLong() else return false
            val newPosition = min(currentPos + seekTime * 1000L, dur - 100)
            player?.seekTo(newPosition.toInt())
            player?.showTextToast("⏩ ${seekTime}s", center = true, duration = 800L)
            Logger.debug { "DoubleTapSeekPatch: seek forward ${seekTime}s" }
            return true
        } catch (e: Throwable) {
            Logger.error(e) { "DoubleTapSeekPatch: failed to seek forward" }
            return false
        }
    }
}