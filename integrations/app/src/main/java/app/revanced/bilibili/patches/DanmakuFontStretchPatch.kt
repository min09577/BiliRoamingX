package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuFontStretchPatch {
    @JvmStatic fun getStretch(): Float = Settings.DanmakuFontStretch.get().coerceIn(0.5f, 2.0f)
}
