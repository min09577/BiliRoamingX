package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object CommentImageCompressPatch {
    private const val TAG = "ImageCompress"
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentImageCompress.get()
    @JvmStatic fun getCompressQuality(): Int = if (isEnabled()) 70 else 100
}
