package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object SearchAutoSuggestLimitPatch {
    @JvmStatic fun getLimit(): Int = Settings.SearchAutoSuggestLimit.get().coerceIn(0, 20)
    @JvmStatic fun isEnabled(): Boolean = getLimit() > 0
}
