package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicAutoLikeAllPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicAutoLikeAll.get()
}
