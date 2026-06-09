package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentImageCompressPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentImageCompress.get()
    @JvmStatic fun getCompressQuality(): Int = if (isEnabled()) 80 else 100
}
