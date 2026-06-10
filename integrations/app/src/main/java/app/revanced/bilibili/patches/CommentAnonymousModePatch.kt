package app.revanced.bilibili.patches

import android.content.Context
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.Toasts

/**
 * 匿名评论模式 — 发评论时不显示用户身份信息
 */
@Keep
object CommentAnonymousModePatch {
    private const val TAG = "CommentAnonymousMode"
    private const val ANONYMOUS_NAME = "匿名用户"
    private const val ANONYMOUS_AVATAR = ""

    /**
     * Check if anonymous mode is enabled.
     */
    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.CommentAnonymousMode.get()
    }

    /**
     * Transform comment request to hide user identity.
     * Called before sending comment.
     */
    @JvmStatic
    fun transformCommentRequest(params: MutableMap<String, String>): Boolean {
        if (!Settings.CommentAnonymousMode.get()) return false

        Logger.debug { "$TAG: anonymizing comment request" }
        // Remove user-identifying fields
        params.remove("sender_uid")
        params.remove("uname")
        params.remove("face")
        params["anonymous"] = "1"
        return true
    }

    /**
     * Transform displayed comment to show anonymous info.
     * Called when rendering comment list.
     */
    @JvmStatic
    fun transformCommentDisplay(commentData: Any?): Boolean {
        if (!Settings.CommentAnonymousMode.get()) return false
        if (commentData == null) return false

        try {
            val clazz = commentData::class.java
            // Try to set anonymous name
            try {
                val nameField = clazz.getDeclaredField("uname")
                nameField.isAccessible = true
                nameField.set(commentData, ANONYMOUS_NAME)
            } catch (_: Throwable) {
                try {
                    val nameField = clazz.getDeclaredField("name")
                    nameField.isAccessible = true
                    nameField.set(commentData, ANONYMOUS_NAME)
                } catch (e: Exception) { Logger.error { "Error in CommentAnonymousModePatch: ${e.message}" } }
            }
            // Try to clear avatar
            try {
                val avatarField = clazz.getDeclaredField("avatar")
                avatarField.isAccessible = true
                avatarField.set(commentData, ANONYMOUS_AVATAR)
            } catch (e: Exception) { Logger.error { "Error in CommentAnonymousModePatch: ${e.message}" } }

            Logger.debug { "$TAG: comment anonymized" }
            return true
        } catch (e: Throwable) {
            Logger.debug { "$TAG: failed to transform comment: $e" }
            return false
        }
    }

    /**
     * Create toggle view for settings UI.
     */
    @JvmStatic
    fun createToggleView(context: Context): View {
        return LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(16, 16, 16, 16)

            addView(CheckBox(context).apply {
                isChecked = Settings.CommentAnonymousMode.get()
                setOnCheckedChangeListener { _, checked ->
                    Settings.CommentAnonymousMode.set(checked)
                    Toasts.showShort(if (checked) "匿名评论已开启" else "匿名评论已关闭")
                    Logger.debug { "$TAG: toggled to $checked" }
                }
            })

            addView(TextView(context).apply {
                text = "匿名评论模式"
                textSize = 16f
                setPadding(8, 0, 0, 0)
            })
        }
    }
}
