package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicAutoBlockKeywordPatch {
    @JvmStatic fun getKeywords(): Set<String> = Settings.DynamicAutoBlockKeyword.get()
    @JvmStatic fun shouldBlock(text: String): Boolean = getKeywords().any { text.contains(it) }
}
