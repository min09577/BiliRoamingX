package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object DanmakuHighlightUserPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuHighlightUser.get().isNotEmpty()
    @JvmStatic fun shouldHighlight(userId: String): Boolean {
        if (!isEnabled()) return false
        return userId in Settings.DanmakuHighlightUser.get()
    }
}
