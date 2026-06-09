package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object CommentHighlightModeratorPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentHighlightModerator.get()
    
    @JvmStatic fun isModerator(memberRole: Int): Boolean {
        if (!isEnabled()) return false
        return memberRole >= 2 // moderator or above
    }
}
