package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuUWBBroadcastPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuUWBBroadcast.get()
}
