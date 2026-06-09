package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object LiveRoomAutoGiftPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.LiveRoomAutoGift.get()
}
