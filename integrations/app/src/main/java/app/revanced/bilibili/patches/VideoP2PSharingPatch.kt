package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoP2PSharingPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoP2PSharing.get()
}
