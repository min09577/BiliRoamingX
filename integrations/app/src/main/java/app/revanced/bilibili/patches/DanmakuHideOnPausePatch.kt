package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object DanmakuHideOnPausePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuHideOnPause.get()
    
    @JvmStatic fun onPlayStateChanged(isPlaying: Boolean): Boolean {
        if (!isEnabled()) return false
        return !isPlaying // hide danmaku when paused
    }
}
