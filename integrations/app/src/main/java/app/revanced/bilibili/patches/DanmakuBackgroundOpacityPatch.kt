package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuBackgroundOpacityPatch {
    @JvmStatic fun getOpacity(): Int = Settings.DanmakuBackgroundOpacity.get().coerceIn(0, 100)
    @JvmStatic fun getAlpha(): Int = (getOpacity() * 255 / 100)
}
