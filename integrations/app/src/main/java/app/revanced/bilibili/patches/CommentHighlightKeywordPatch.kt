package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentHighlightKeywordPatch {
    @JvmStatic fun getKeywords(): Set<String> = Settings.CommentHighlightKeyword.get()
    @JvmStatic fun shouldHighlight(text: String): Boolean = getKeywords().any { text.contains(it) }
}
