package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedSortPatch {
    const val SORT_DEFAULT = 0; const val SORT_LATEST = 1; const val SORT_POPULAR = 2; const val SORT_RECOMMENDED = 3
    /**
     * getSortMode
     */
    @JvmStatic fun getSortMode(): Int = Settings.HomeFeedSort.get().coerceIn(0, 3)
    /**
     * getSortDescription
     */
    @JvmStatic fun getSortDescription(): String = when (getSortMode()) {
        SORT_LATEST -> "最新"; SORT_POPULAR -> "最热"; SORT_RECOMMENDED -> "推荐"; else -> "默认"
    }
}
