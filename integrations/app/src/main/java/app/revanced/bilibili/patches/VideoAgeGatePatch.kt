package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoAgeGatePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoAgeGate.get()
}
