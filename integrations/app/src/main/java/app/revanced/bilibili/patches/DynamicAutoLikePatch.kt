package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicAutoLikePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicAutoLike.get()
    /**
     * shouldAutoLike
     */
    @JvmStatic fun shouldAutoLike(): Boolean = isEnabled()
}
