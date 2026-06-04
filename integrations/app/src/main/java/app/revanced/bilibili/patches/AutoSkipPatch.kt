package app.revanced.bilibili.patches

import android.content.Context
import androidx.annotation.Keep
import app.revanced.bilibili.patches.main.VideoInfoHolder
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.PlayerHookProvider.seekTo

@Keep
object AutoSkipPatch {
    private var lastAid: Long = 0
    private var lastCid: Long = 0
    private var introSkipped = false
    private var outroSkipped = false

    /**
     * Called when video starts playing.
     * Checks if we should skip intro.
     */
    @JvmStatic
    fun onVideoStart(player: Any?, duration: Long) {
        if (!Settings.AutoSkipIntro.get()) return
        if (player == null) return

        val info = VideoInfoHolder.current ?: return
        val cid = info.cid
        val view = info.view ?: return
        val aid = when (view) {
            is com.bapis.bilibili.app.view.v1.ViewReply -> view.arc.aid
            is com.bapis.bilibili.app.viewunite.v1.ViewReply -> view.arc.aid
            else -> return
        }

        // Reset skip state for new video
        if (aid != lastAid || cid != lastCid) {
            lastAid = aid
            lastCid = cid
            introSkipped = false
            outroSkipped = false
        }

        // Skip intro
        if (!introSkipped) {
            val introDuration = Settings.AutoSkipIntroDuration.get()
            if (introDuration > 0) {
                try {
                    player.seekTo(introDuration * 1000)
                    introSkipped = true
                    Logger.debug { "AutoSkipPatch: Skipped intro ${introDuration}s" }
                } catch (e: Throwable) {
                    Logger.error(e) { "AutoSkipPatch: Failed to skip intro" }
                }
            }
        }
    }

    /**
     * Called periodically during playback.
     * Checks if we should skip outro.
     */
    @JvmStatic
    fun onPlaybackProgress(player: Any?, currentPosition: Long, duration: Long) {
        if (!Settings.AutoSkipOutro.get()) return
        if (player == null) return
        if (outroSkipped) return

        val outroDuration = Settings.AutoSkipOutroDuration.get()
        if (outroDuration <= 0) return

        // Check if we're near the end
        val remainingMs = duration - currentPosition
        val outroMs = outroDuration * 1000L
        if (remainingMs in 1..outroMs) {
            try {
                // Seek to end, which will trigger next video or end
                player.seekTo((duration - 100).toInt())
                outroSkipped = true
                Logger.debug { "AutoSkipPatch: Skipped outro at ${currentPosition}ms" }
            } catch (e: Throwable) {
                Logger.error(e) { "AutoSkipPatch: Failed to skip outro" }
            }
        }
    }

    /**
     * Called when video ends.
     */
    @JvmStatic
    fun onVideoEnd() {
        introSkipped = false
        outroSkipped = false
    }
}
