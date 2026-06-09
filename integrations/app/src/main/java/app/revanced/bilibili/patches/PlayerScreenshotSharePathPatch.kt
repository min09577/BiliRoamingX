package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object PlayerScreenshotSharePathPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerScreenshotSharePath.get().isNotEmpty()
    @JvmStatic fun getSharePath(): String = Settings.PlayerScreenshotSharePath.get()
}
