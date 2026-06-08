package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuSendQueuePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuSendQueue.get()
}
