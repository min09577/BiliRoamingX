package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object CommentSortByLengthPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentSortByLength.get()
    
    @JvmStatic fun compareByLength(a: String, b: String): Int = b.length - a.length
}
