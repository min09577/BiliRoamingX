package app.revanced.bilibili.patches

import android.content.Context
import android.view.View
import android.widget.GridView
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 评论区表情键盘增强 — 在评论输入框旁添加常用表情快速选择面板
 * 长按表情按钮显示分类表情列表
 */
@Keep
object CommentEmojiKeyboardPatch {
    private const val TAG = "EmojiKeyboard"

    // Common emoji sets
    private val COMMON_EMOJIS = listOf(
        "😊", "😂", "🤣", "❤️", "😍", "🤔", "😭", "👍",
        "🎉", "🔥", "✨", "💪", "🙌", "👏", "😍", "🥰",
        "😘", "😋", "😎", "🤩", "💯", "⭐", "🎬", "🎵",
        "🌸", "🍺", "☕", "🎂", "🎮", "📚", "🐱", "🐶"
    )

    private val CATEGORY_BILLEMOJIS = listOf(
        "b_emoji_1", "b_emoji_2", "b_emoji_3"
    )

    /**
     * Check if enhanced emoji keyboard is enabled.
     */
    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.CommentEmojiKeyboard.get()
    }

    /**
     * Hook target: get common emoji list for quick panel.
     * @return list of emoji strings
     */
    @JvmStatic
    fun getCommonEmojis(): List<String> {
        return COMMON_EMOJIS
    }

    /**
     * Hook target: get bilibili custom emoji categories.
     * @return list of category IDs
     */
    @JvmStatic
    fun getEmojiCategories(): List<String> {
        return CATEGORY_BILLEMOJIS
    }

    /**
     * Hook target: create the emoji quick panel view.
     * Called when user long-presses the emoji button.
     */
    @JvmStatic
    fun createEmojiPanel(context: Context, onEmojiSelected: (String) -> Unit): View {
        Logger.debug { "$TAG: creating emoji panel" }

        return GridView(context).apply {
            numColumns = 8
            adapter = object : BaseAdapter() {
                override fun getCount() = COMMON_EMOJIS.size
                override fun getItem(position: Int) = COMMON_EMOJIS[position]
                override fun getItemId(position: Int) = position.toLong()
                override fun getView(position: Int, convertView: View?, parent: android.view.ViewGroup): View {
                    val tv = convertView as? TextView ?: TextView(context).apply {
                        textSize = 24f
                        gravity = android.view.Gravity.CENTER
                        setPadding(8, 8, 8, 8)
                    }
                    tv.text = COMMON_EMOJIS[position]
                    tv.setOnClickListener { onEmojiSelected(COMMON_EMOJIS[position]) }
                    return tv
                }
            }
            setOnItemClickListener { _, _, position, _ ->
                onEmojiSelected(COMMON_EMOJIS[position])
            }
        }
    }
}
