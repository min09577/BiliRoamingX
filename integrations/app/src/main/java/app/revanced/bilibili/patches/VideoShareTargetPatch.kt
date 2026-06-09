package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoShareTargetPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoShareTarget.get()
}
