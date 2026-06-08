package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerDiamondBadgePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerDiamondBadge.get()
}
