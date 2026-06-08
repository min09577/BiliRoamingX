package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object DanmakuHighlightUserPatch {
    private const val TAG = "HighlightUser"
    @JvmStatic fun getHighlightedUsers(): Set<String> = Settings.DanmakuHighlightUser.get()
    @JvmStatic fun isHighlighted(userId: String): Boolean = getHighlightedUsers().contains(userId)
}
