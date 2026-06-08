package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 评论语音输入 — 支持在评论区使用语音转文字输入
 * 调用系统语音识别API
 */
@Keep
object CommentVoiceInputPatch {
    private const val TAG = "VoiceInput"

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.CommentVoiceInput.get()
    }

    @JvmStatic
    fun shouldShowVoiceButton(): Boolean {
        val show = isEnabled()
        Logger.debug { "$TAG: show voice button=$show" }
        return show
    }

    @JvmStatic
    fun onVoiceInputResult(text: String): String {
        Logger.debug { "$TAG: voice input result: $text" }
        return text
    }
}