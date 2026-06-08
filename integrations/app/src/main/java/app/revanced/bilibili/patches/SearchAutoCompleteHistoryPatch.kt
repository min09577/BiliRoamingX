package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object SearchAutoCompleteHistoryPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.SearchAutoCompleteHistory.get()
}
