package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoAutoSkipMidrollPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoAutoSkipMidroll.get()
}
