package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoBlockchainVerifyPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoBlockchainVerify.get()
}
