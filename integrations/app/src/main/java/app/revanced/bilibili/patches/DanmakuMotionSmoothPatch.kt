package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuMotionSmoothPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuMotionSmooth.get()
}
