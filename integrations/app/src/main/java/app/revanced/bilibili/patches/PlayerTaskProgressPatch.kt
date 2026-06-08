package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerTaskProgressPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerTaskProgress.get()
}
