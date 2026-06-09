package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedMetaverseConnectPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedMetaverseConnect.get()
}
