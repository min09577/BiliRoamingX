package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerScreenshotSharePathPatch {
    @JvmStatic fun getPath(): String = Settings.PlayerScreenshotSharePath.get()
    @JvmStatic fun hasCustomPath(): Boolean = getPath().isNotBlank()
}
