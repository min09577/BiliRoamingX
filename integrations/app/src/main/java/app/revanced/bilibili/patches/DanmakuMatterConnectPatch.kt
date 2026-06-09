package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuMatterConnectPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuMatterConnect.get()
}
