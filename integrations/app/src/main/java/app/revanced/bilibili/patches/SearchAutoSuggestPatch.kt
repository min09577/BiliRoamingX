package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object SearchAutoSuggestPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.SearchAutoSuggest.get()
}
