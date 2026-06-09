package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuBackgroundSyncPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuBackgroundSync.get()
}
