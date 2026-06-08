package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object LiveDanmakuSpeedControlPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.LiveDanmakuSpeedControl.get()
}
