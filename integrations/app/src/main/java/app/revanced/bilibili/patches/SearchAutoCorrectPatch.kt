package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object SearchAutoCorrectPatch {
    private const val TAG = "AutoCorrect"
    private val CORRECTIONS = mapOf("biliblli" to "bilibili", "bliibili" to "bilibili")
    @JvmStatic fun isEnabled(): Boolean = Settings.SearchAutoCorrect.get()
    @JvmStatic fun correct(query: String): String {
        if (!isEnabled()) return query
        var result = query
        CORRECTIONS.forEach { (wrong, right) -> result = result.replace(wrong, right, true) }
        return result
    }
}
