package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuSendCooldownPatch {
    @JvmStatic fun getCooldownSeconds(): Int = Settings.DanmakuSendCooldown.get().coerceIn(0, 60)
    @JvmStatic fun hasCooldown(): Boolean = getCooldownSeconds() > 0
}
