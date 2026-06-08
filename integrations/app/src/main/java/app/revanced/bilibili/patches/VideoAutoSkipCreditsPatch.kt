package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoAutoSkipCreditsPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoAutoSkipCredits.get()
    @JvmStatic fun shouldSkipCredits(): Boolean = isEnabled()
}
