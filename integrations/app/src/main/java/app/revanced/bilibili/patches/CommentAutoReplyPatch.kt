package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 评论自动回复模板 — 在评论区快速插入预设回复模板
 * 支持多套模板，长按回复按钮时显示模板列表
 */
@Keep
object CommentAutoReplyPatch {
    private const val TAG = "CommentAutoReply"
    // Default templates
    private val DEFAULT_TEMPLATES = listOf(
        "感谢分享！",
        "学到了！",
        "支持一下",
        "mark",
        "👍"
    )

    /**
     * Check if auto-reply is enabled (has non-empty template).
     */
    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.CommentAutoReply.get().isNotBlank()
    }

    /**
     * Get the configured reply template text.
     * @return template text, or empty if not configured
     */
    @JvmStatic
    fun getTemplate(): String {
        return Settings.CommentAutoReply.get().trim()
    }

    /**
     * Get all configured templates (comma-separated in setting).
     * @return list of templates
     */
    @JvmStatic
    fun getTemplates(): List<String> {
        val config = Settings.CommentAutoReply.get().trim()
        if (config.isBlank()) return DEFAULT_TEMPLATES

        return config.split("\n").map { it.trim() }.filter { it.isNotEmpty() }
    }

    /**
     * Hook target: fill comment input with template text.
     * Called when user long-presses reply button.
     * @param templateIndex which template to use (0-based)
     * @return the template text to insert
     */
    @JvmStatic
    fun getReplyText(templateIndex: Int = 0): String {
        val templates = getTemplates()
        val text = templates.getOrElse(templateIndex) { "" }
        Logger.debug { "$TAG: reply template $templateIndex = '$text'" }
        return text
    }

    /**
     * Hook target: get quick reply options for UI display.
     * @return array of template texts for selection
     */
    @JvmStatic
    fun getQuickReplies(): Array<String> {
        return getTemplates().toTypedArray()
    }

    /**
     * Hook target: check if should show template picker.
     * Called on long press of comment reply button.
     */
    @JvmStatic
    fun shouldShowPicker(): Boolean {
        return Settings.CommentAutoReply.get().isNotBlank()
    }
}
