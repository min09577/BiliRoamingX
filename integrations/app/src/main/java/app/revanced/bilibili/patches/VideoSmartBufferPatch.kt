package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoSmartBufferPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoSmartBuffer.get()
}
