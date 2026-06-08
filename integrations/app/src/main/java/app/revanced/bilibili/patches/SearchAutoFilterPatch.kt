package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object SearchAutoFilterPatch {
    @JvmStatic fun getFilterKeywords(): Set<String> = Settings.SearchAutoFilter.get()
    @JvmStatic fun shouldFilter(text: String): Boolean = getFilterKeywords().any { text.contains(it) }
}
