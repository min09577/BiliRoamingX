package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicAutoTranslatePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicAutoTranslate.get()
    @JvmStatic fun shouldAutoTranslate(): Boolean = isEnabled()
}
