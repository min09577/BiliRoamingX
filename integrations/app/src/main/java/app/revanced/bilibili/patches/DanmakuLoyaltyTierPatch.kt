package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuLoyaltyTierPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuLoyaltyTier.get()
}
