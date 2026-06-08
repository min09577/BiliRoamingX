package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object LiveDanmakuFilterKeywordsPatch {
    @JvmStatic fun getKeywords(): Set<String> = Settings.LiveDanmakuFilterKeywords.get()
    @JvmStatic fun shouldFilter(text: String): Boolean = getKeywords().any { text.contains(it) }
}
