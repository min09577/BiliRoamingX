package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedHLSStreamPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedHLSStream.get()
}
