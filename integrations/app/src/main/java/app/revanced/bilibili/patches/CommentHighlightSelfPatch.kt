package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object CommentHighlightSelfPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentHighlightSelf.get()

    /**
     * isSelfComment
     */
    @JvmStatic fun isSelfComment(mid: Long): Boolean {
        if (!isEnabled()) return false
        return mid == getCurrentUserId()
    }

    private fun getCurrentUserId(): Long {
        return try {
            val clazz = try { Class.forName("tv.danmaku.bili.AccountHelper") } catch (_: Exception) { null }
            clazz?.getMethod("getMid")?.invoke(null) as? Long ?: 0L
        } catch (_: Exception) { 0L }
    }
}
