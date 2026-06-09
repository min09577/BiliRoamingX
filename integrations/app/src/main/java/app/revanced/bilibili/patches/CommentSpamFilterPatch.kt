package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object CommentSpamFilterPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentSpamFilter.get()
    
    private val spamPatterns = listOf("加微信", "加QQ", "私聊", "免费领", "点击链接", "优惠券")
    
    @JvmStatic fun isSpam(text: String): Boolean {
        if (!isEnabled()) return false
        return spamPatterns.any { text.contains(it) }
    }
}
