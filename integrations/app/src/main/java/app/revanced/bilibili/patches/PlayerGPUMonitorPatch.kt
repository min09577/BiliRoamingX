package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerGPUMonitorPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerGPUMonitor.get()
}
