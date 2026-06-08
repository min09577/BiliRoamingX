package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuSendDelayPatch {
    @JvmStatic fun getDelayMs(): Int = Settings.DanmakuSendDelay.get().coerceIn(0, 5000)
    @JvmStatic fun hasDelay(): Boolean = getDelayMs() > 0
}
