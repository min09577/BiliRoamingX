package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerScreenshotFormatCustomPatch {
    const val FORMAT_PNG = 0; const val FORMAT_JPEG = 1; const val FORMAT_WEBP = 2
    @JvmStatic fun getFormat(): Int = Settings.PlayerScreenshotFormatCustom.get().coerceIn(0, 2)
    @JvmStatic fun getFormatName(): String = when (getFormat()) { FORMAT_JPEG -> "JPEG"; FORMAT_WEBP -> "WEBP"; else -> "PNG" }
}
