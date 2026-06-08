package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object LiveAutoRecordPatch {
    private const val TAG = "LiveAutoRecord"
    @JvmStatic fun isEnabled(): Boolean = Settings.LiveAutoRecord.get()
    @JvmStatic fun shouldAutoRecord(): Boolean {
        val record = isEnabled()
        Logger.debug { "$TAG: auto record=$record" }
        return record
    }
}
