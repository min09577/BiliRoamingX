package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerControlFadeOutPatch {
    @JvmStatic fun getFadeOutSeconds(): Int = Settings.PlayerControlFadeOut.get().coerceIn(1, 10)
    @JvmStatic fun getFadeOutMs(): Long = getFadeOutSeconds() * 1000L
}
