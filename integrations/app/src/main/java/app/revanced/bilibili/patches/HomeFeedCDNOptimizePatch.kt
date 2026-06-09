package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedCDNOptimizePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedCDNOptimize.get()
}
