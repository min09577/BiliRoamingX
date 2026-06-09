package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentCryptoTipPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentCryptoTip.get()
}
