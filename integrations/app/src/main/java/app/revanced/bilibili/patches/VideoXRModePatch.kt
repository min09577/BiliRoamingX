package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoXRModePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoXRMode.get()
}
