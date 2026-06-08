package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoAutoRepeatSegmentPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoAutoRepeatSegment.get()
}
