package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object LiveDanmakuFilterUserPatch {
    /**
     * getFilteredUsers
     */
    @JvmStatic fun getFilteredUsers(): Set<String> = Settings.LiveDanmakuFilterUser.get()
    /**
     * shouldFilter
     */
    @JvmStatic fun shouldFilter(userId: String): Boolean = getFilteredUsers().contains(userId)
}
