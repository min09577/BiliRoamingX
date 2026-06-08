package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerScreenshotPathPatch {
    @JvmStatic fun getPath(): String = Settings.PlayerScreenshotPath.get()
    @JvmStatic fun hasCustomPath(): Boolean = getPath().isNotBlank()
}
