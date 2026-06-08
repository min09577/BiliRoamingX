package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuCloudSyncPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuCloudSync.get()
}
