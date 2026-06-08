package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoStorageManagerPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoStorageManager.get()
}
