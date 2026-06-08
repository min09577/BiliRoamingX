package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentDraftAutoSavePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentDraftAutoSave.get()
    @JvmStatic fun onSaveDraft(text: String) { if (isEnabled()) { /* save */ } }
}
