package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerControlAutoHidePatch {
    @JvmStatic fun getAutoHideSeconds(): Int = Settings.PlayerControlAutoHide.get().coerceIn(1, 10)
    @JvmStatic fun getAutoHideMs(): Long = getAutoHideSeconds() * 1000L
}
