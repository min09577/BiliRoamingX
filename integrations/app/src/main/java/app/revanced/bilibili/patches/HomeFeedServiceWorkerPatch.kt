package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedServiceWorkerPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedServiceWorker.get()
}
