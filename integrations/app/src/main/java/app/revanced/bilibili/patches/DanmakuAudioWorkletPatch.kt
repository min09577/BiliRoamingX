package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuAudioWorkletPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuAudioWorklet.get()
}
