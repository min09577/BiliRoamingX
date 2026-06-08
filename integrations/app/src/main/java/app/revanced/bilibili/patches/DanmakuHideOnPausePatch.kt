package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuHideOnPausePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuHideOnPause.get()
}
