package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object LiveRoomAutoReconnectPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.LiveRoomAutoReconnect.get()
    @JvmStatic fun getMaxRetries(): Int = 3
}
