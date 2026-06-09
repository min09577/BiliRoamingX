package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuGPSOverlayPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuGPSOverlay.get()
}
