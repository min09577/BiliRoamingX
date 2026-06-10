package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerControlFadeOutPatch {
    /**
     * getFadeOutSeconds
     */
    @JvmStatic fun getFadeOutSeconds(): Int = Settings.PlayerControlFadeOut.get().coerceIn(1, 10)
    /**
     * getFadeOutMs
     */
    @JvmStatic fun getFadeOutMs(): Long = getFadeOutSeconds() * 1000L
}
