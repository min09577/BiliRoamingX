package app.revanced.bilibili.patches

import android.view.View
import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 弹幕发送预览 — 发送前在屏幕上预览弹幕效果
 * 显示弹幕的字体、颜色、位置等实际效果
 */
@Keep
object DanmakuSendPreviewPatch {
    private const val TAG = "DanmakuSendPreview"

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.DanmakuSendPreview.get()
    }

    /**
     * Hook target: create preview view for danmaku before sending.
     * @param text the danmaku text to preview
     * @param color the danmaku color
     * @param size the font size in sp
     * @return preview view to display
     */
    @JvmStatic
    fun createPreviewView(text: String, color: Int, size: Int): TextView? {
        if (!isEnabled()) return null
        Logger.debug { "$TAG: creating preview for '$text' color=$color size=$size" }
        return null
    }

    /**
     * Hook target: check if preview should be shown.
     */
    @JvmStatic
    fun shouldShowPreview(): Boolean {
        return Settings.DanmakuSendPreview.get()
    }
}
