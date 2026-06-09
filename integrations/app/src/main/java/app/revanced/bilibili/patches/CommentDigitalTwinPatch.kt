package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentDigitalTwinPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentDigitalTwin.get()
}
