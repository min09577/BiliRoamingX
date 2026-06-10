package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object LiveAutoRecordPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.LiveAutoRecord.get()
    /**
     * shouldAutoRecord
     */
    @JvmStatic fun shouldAutoRecord(): Boolean = isEnabled()
}
