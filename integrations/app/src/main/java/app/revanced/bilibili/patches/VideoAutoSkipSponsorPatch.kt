package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoAutoSkipSponsorPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoAutoSkipSponsor.get()
    /**
     * shouldSkipSponsor
     */
    @JvmStatic fun shouldSkipSponsor(): Boolean = isEnabled()
}
