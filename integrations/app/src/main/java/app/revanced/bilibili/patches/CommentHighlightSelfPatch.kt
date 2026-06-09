package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object CommentHighlightSelfPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentHighlightSelf.get()
    
    @JvmStatic fun isSelfComment(mid: Long): Boolean {
        if (!isEnabled()) return false
        return mid == getCurrentUserId()
    }
    
    private fun getCurrentUserId(): Long {
        return try {
            val clazz = Class.forName("tv.danmaku.bili.AccountHelper")
            clazz.getMethod("getMid").invoke(null) as Long
        } catch (_: Exception) { 0L }
    }
}
