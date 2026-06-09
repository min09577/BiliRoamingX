package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedDolbyVisionPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedDolbyVision.get()
}
