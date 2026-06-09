package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuAbusiveFilterPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuAbusiveFilter.get()
}
