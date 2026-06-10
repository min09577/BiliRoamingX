package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuFilterDuplicatePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuFilterDuplicate.get()
    private val recentDanmaku = LinkedHashSet<String>()
    /**
     * isDuplicate
     */
    @JvmStatic fun isDuplicate(text: String): Boolean {
        if (!isEnabled()) return false
        val dup = recentDanmaku.contains(text)
        recentDanmaku.add(text)
        if (recentDanmaku.size > 100) recentDanmaku.iterator().let { it.next(); it.remove() }
        return dup
    }
}
