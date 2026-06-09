package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerPrivacyModePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerPrivacyMode.get()
}
