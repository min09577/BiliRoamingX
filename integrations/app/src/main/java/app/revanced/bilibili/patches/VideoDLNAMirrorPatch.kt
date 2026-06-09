package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoDLNAMirrorPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoDLNAMirror.get()
}
