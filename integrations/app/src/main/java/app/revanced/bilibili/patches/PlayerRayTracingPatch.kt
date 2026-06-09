package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerRayTracingPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerRayTracing.get()
}
