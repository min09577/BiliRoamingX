package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentHighlightSelfPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentHighlightSelf.get()
    @JvmStatic fun shouldHighlight(userId: String, selfId: String): Boolean = isEnabled() && userId == selfId
}
