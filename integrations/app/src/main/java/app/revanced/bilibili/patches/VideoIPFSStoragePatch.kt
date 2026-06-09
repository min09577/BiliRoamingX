package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoIPFSStoragePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoIPFSStorage.get()
}
