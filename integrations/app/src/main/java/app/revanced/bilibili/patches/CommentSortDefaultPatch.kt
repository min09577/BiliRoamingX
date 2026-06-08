package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object CommentSortDefaultPatch {
    private const val TAG = "CommentSort"
    const val SORT_DEFAULT = 0
    const val SORT_HOT = 1
    const val SORT_TIME = 2
    const val SORT_LIKE = 3
    @JvmStatic fun getSortMode(): Int = Settings.CommentSortDefault.get().coerceIn(0, 3)
    @JvmStatic fun getSortDescription(): String = when (getSortMode()) {
        SORT_HOT -> "最热"; SORT_TIME -> "最新"; SORT_LIKE -> "最赞"; else -> "默认"
    }
}
