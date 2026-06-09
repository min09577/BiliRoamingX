package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedParentalControlPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedParentalControl.get()
}
