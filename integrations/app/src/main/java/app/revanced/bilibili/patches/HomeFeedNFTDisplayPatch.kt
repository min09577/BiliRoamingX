package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedNFTDisplayPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedNFTDisplay.get()
}
