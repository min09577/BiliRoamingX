package app.revanced.bilibili.patches

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.Toasts

@Keep
object CommentExpandAllPatch {

    @JvmStatic
    fun expandAllComments(view: View?, depth: Int = 0) {
        if (!Settings.CommentExpandAll.get()) return
        if (view == null || depth > 15) return

        try {
            if (view is ViewGroup) {
                for (i in 0 until view.childCount) {
                    expandAllComments(view.getChildAt(i), depth + 1)
                }
            }

            // Find TextViews that have maxLines set (collapsed comments)
            if (view is TextView && view.maxLines in 1..10) {
                view.maxLines = Integer.MAX_VALUE
                view.ellipsize = null
                Logger.debug { "CommentExpandAll: expanded comment" }
            }
        } catch (e: Throwable) {
            Logger.error(e) { "CommentExpandAll: failed to expand" }
        }
    }

    @JvmStatic
    fun onExpandButtonClick(view: View?) {
        expandAllComments(view)
        Toasts.showShort("已展开所有评论")
    }

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.CommentExpandAll.get()
    }
}
