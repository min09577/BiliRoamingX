package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentHighlightKeywordPatch {
    /**
     * getKeywords
     */
    @JvmStatic fun getKeywords(): Set<String> = Settings.CommentHighlightKeyword.get()
    /**
     * shouldHighlight
     */
    @JvmStatic fun shouldHighlight(text: String): Boolean = getKeywords().any { text.contains(it) }
}
