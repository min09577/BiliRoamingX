package app.revanced.bilibili.patches

import android.view.SurfaceView
import android.view.View
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 纯音频模式 — 只播放视频声音，隐藏画面以节省流量和性能
 * 适用于听音乐、播客等场景
 */
@Keep
object AudioOnlyModePatch {
    private const val TAG = "AudioOnly"
    private var isAudioOnlyActive = false

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.AudioOnlyMode.get()
    }

    @JvmStatic
    fun isAudioOnlyActive(): Boolean {
        return isAudioOnlyActive && isEnabled()
    }

    @JvmStatic
    fun applyAudioOnly(surfaceView: SurfaceView?) {
        if (!isEnabled()) {
            if (isAudioOnlyActive) {
                restoreVideo(surfaceView)
            }
            return
        }

        isAudioOnlyActive = true
        if (surfaceView != null) {
            surfaceView.visibility = View.GONE
            Logger.debug { "$TAG: video surface hidden" }
        }
    }

    @JvmStatic
    fun restoreVideo(surfaceView: SurfaceView?) {
        isAudioOnlyActive = false
        if (surfaceView != null) {
            surfaceView.visibility = View.VISIBLE
            Logger.debug { "$TAG: video surface restored" }
        }
    }

    @JvmStatic
    fun shouldBlockVideoTrack(): Boolean {
        val block = isEnabled()
        Logger.debug { "$TAG: block video track=$block" }
        return block
    }

    @JvmStatic
    fun getModeDescription(): String {
        return if (isEnabled()) "纯音频模式" else "正常模式"
    }
}