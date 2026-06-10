package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object LiveRoomAutoFollowPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.LiveRoomAutoFollow.get()
    /**
     * shouldAutoFollow
     */
    @JvmStatic fun shouldAutoFollow(): Boolean = isEnabled()
}
