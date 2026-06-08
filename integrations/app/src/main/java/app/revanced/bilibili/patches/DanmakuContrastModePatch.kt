package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuContrastModePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuContrastMode.get()
}
