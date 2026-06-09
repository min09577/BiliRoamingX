package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedExclusiveContentPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedExclusiveContent.get()
}
