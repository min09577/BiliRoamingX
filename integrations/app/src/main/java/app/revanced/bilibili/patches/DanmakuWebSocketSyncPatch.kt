package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuWebSocketSyncPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuWebSocketSync.get()
}
