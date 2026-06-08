package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuColorContrastPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuColorContrast.get()
}
