package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoDigitalWellbeingPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoDigitalWellbeing.get()
}
