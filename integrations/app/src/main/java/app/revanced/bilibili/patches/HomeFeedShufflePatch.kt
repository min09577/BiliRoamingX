package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedShufflePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedShuffle.get()
}
