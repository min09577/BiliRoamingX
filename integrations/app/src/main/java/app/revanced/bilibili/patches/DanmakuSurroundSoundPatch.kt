package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuSurroundSoundPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuSurroundSound.get()
}
