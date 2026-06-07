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
 * 评论区贴纸键盘增强 — 在评论输入框旁添加B站特色贴纸快速选择面板
 * 与CommentEmojiKeyboard互补，贴纸为B站专属动态表情
 */
@Keep
object CommentStickerKeyboardPatch {
    private const val TAG = "StickerKeyboard"

    // Popular bilibili stickers
    private val COMMON_STICKERS = listOf(
        "bili_滑稽", "bili_笑哭", "bili_狗头", "bili_偷笑",
        "bili_捂脸", "bili_破防", "bili_暴富", "bili_牛蛙",
        "bili_点赞", "bili_投币", "bili_收藏", "bili_转发",
        "bili_打call", "bili_催更", "bili_awsl", "bili_爷青回"
    )

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.CommentStickerKeyboard.get()
    }

    @JvmStatic
    fun getStickers(): List<String> {
        return COMMON_STICKERS
    }

    @JvmStatic
    fun createStickerPanel(context: Context, onStickerSelected: (String) -> Unit): View {
        Logger.debug { "$TAG: creating sticker panel" }
        return GridView(context).apply {
            numColumns = 4
            adapter = object : BaseAdapter() {
                override fun getCount() = COMMON_STICKERS.size
                override fun getItem(position: Int) = COMMON_STICKERS[position]
                override fun getItemId(position: Int) = position.toLong()
                override fun getView(position: Int, convertView: View?, parent: android.view.ViewGroup): View {
                    val tv = convertView as? TextView ?: TextView(context).apply {
                        textSize = 14f
                        gravity = android.view.Gravity.CENTER
                        setPadding(12, 12, 12, 12)
                    }
                    tv.text = COMMON_STICKERS[position]
                    tv.setOnClickListener { onStickerSelected(COMMON_STICKERS[position]) }
                    return tv
                }
            }
            setOnItemClickListener { _, _, position, _ ->
                onStickerSelected(COMMON_STICKERS[position])
            }
        }
    }
}
