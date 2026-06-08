package app.revanced.bilibili.patches

import android.content.Context
import android.view.View
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 评论区GIF动图表情面板 — 在评论输入框旁添加GIF动图表情选择面板
 * 与CommentEmojiKeyboard和CommentStickerKeyboard互补
 */
@Keep
object CommentGifStickerPatch {
    private const val TAG = "GifSticker"

    private val GIF_CATEGORIES = listOf(
        "热门" to listOf("笑哭", "狗头", "滑稽", "偷笑"),
        "表情" to listOf("害羞", "生气", "大哭", "开心"),
        "动作" to listOf("鼓掌", "加油", "比心", "拜拜"),
        "其他" to listOf("干杯", "666", "awsl", "好家伙")
    )

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.CommentGifSticker.get()
    }

    @JvmStatic
    fun getCategories(): List<Pair<String, List<String>>> {
        return GIF_CATEGORIES
    }

    @JvmStatic
    fun createGifPanel(context: Context, onGifSelected: (String) -> Unit): View? {
        if (!isEnabled()) return null
        Logger.debug { "$TAG: creating GIF panel" }
        return null // UI hook point
    }
}