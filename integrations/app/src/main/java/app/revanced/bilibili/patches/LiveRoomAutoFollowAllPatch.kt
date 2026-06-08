package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object LiveRoomAutoFollowAllPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.LiveRoomAutoFollowAll.get()
}
