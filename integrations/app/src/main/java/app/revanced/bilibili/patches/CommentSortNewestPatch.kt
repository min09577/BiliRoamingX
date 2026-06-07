package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object CommentSortNewestPatch {

    @JvmStatic
    fun shouldSortByNewest(): Boolean {
        return Settings.CommentSortNewest.get()
    }

    @JvmStatic
    fun getSortType(): Int {
        // 0 = default, 1 = by time (newest first), 2 = by likes
        return if (Settings.CommentSortNewest.get()) 1 else 0
    }
}
