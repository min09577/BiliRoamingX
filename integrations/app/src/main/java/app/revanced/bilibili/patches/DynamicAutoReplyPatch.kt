package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DynamicAutoReplyPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicAutoReply.get()
}
