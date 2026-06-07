package app.revanced.bilibili.patches

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object CommentDraftPatch {

    private const val PREFS_NAME = "comment_drafts"
    private var currentDraft = ""

    @JvmStatic
    fun onSaveDraft(context: Context?, videoId: String?, draft: String?) {
        if (!Settings.CommentDraft.get()) return
        if (context == null || videoId == null || draft.isNullOrEmpty()) return

        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putString(videoId, draft).apply()
            currentDraft = draft
            Logger.debug { "CommentDraft: saved draft for $videoId" }
        } catch (e: Throwable) {
            Logger.error(e) { "CommentDraft: failed to save" }
        }
    }

    @JvmStatic
    fun onLoadDraft(context: Context?, videoId: String?): String? {
        if (!Settings.CommentDraft.get()) return null
        if (context == null || videoId == null) return null

        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val draft = prefs.getString(videoId, null)
            if (!draft.isNullOrEmpty()) {
                currentDraft = draft
                Logger.debug { "CommentDraft: loaded draft for $videoId" }
            }
            return draft
        } catch (e: Throwable) {
            Logger.error(e) { "CommentDraft: failed to load" }
            return null
        }
    }

    @JvmStatic
    fun onClearDraft(context: Context?, videoId: String?) {
        if (context == null || videoId == null) return
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().remove(videoId).apply()
            currentDraft = ""
            Logger.debug { "CommentDraft: cleared draft for $videoId" }
        } catch (e: Throwable) {
            Logger.error(e) { "CommentDraft: failed to clear" }
        }
    }

    @JvmStatic
    fun getCurrentDraft(): String = currentDraft
}
