package app.revanced.bilibili.patches

import android.app.AlertDialog
import android.content.Context
import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.*

@Keep
object CommentTranslatePatch {
    private var lastTranslator: Translator = GoogleTranslator

    /**
     * Translate a comment text and show the result in a dialog.
     */
    @JvmStatic
    fun translateComment(context: Context, text: String) {
        if (!Settings.CommentTranslate.get()) return
        try {
            val translator = getTranslator()
            Utils.async {
                val translated = translator.translate(text)
                if (translated != null) {
                    Utils.runOnMainThread {
                        showTranslationDialog(context, text, translated)
                    }
                } else {
                    Utils.runOnMainThread {
                        Toasts.showShort("翻译失败，请稍后重试")
                    }
                }
            }
        } catch (e: Throwable) {
            Logger.error(e) { "CommentTranslatePatch: translation failed" }
            Toasts.showShort("翻译失败: ${e.message}")
        }
    }

    /**
     * Show translation result dialog.
     */
    private fun showTranslationDialog(context: Context, original: String, translated: String) {
        val appDialogTheme = Utils.getResId("AppTheme.Dialog.Alert", "style")
        val message = buildString {
            appendLine("【翻译结果】")
            appendLine(translated)
            appendLine()
            appendLine("【原文】")
            append(original)
        }
        AlertDialog.Builder(context, appDialogTheme)
            .setTitle("评论翻译")
            .setMessage(message)
            .setPositiveButton("复制翻译") { _, _ ->
                setClipboardContent(content = translated)
                Toasts.showShort("已复制翻译内容")
            }
            .setNeutralButton("复制原文") { _, _ ->
                setClipboardContent(content = original)
                Toasts.showShort("已复制原文内容")
            }
            .setNegativeButton(android.R.string.cancel, null)
            .create()
            .constraintSize()
            .apply { show() }
            .also {
                (it.findViewById<TextView>(android.R.id.message))?.run {
                    setTextIsSelectable(true)
                }
            }
    }

    /**
     * Get the translator based on settings.
     */
    private fun getTranslator(): Translator {
        return if (Settings.SubtitleTranslateServer.get() == "microsoft") {
            MicrosoftTranslator
        } else {
            GoogleTranslator
        }
    }

    /**
     * Add translate option to comment long-click menu.
     */
    @JvmStatic
    fun onCommentLongClick(context: Context, text: String): Boolean {
        if (!Settings.CommentTranslate.get()) return false
        translateComment(context, text)
        return true
    }
}
