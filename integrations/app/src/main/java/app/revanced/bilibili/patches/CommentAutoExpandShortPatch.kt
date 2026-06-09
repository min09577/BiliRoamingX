package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object CommentAutoExpandShortPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentAutoExpandShort.get()
    
    @JvmStatic fun shouldExpand(text: String): Boolean {
        if (!isEnabled()) return false
        return text.length < 50 // auto expand short comments
    }
}
