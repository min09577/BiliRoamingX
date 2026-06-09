package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoDesktopWidgetPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoDesktopWidget.get()
}
