package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object PlayerSubtitleSyncPatch {
    private const val TAG = "SubtitleSync"
    @JvmStatic fun getSyncOffsetMs(): Int = Settings.PlayerSubtitleSync.get()
    @JvmStatic fun applySync(subtitleTimeMs: Long): Long = subtitleTimeMs + getSyncOffsetMs()
    @JvmStatic fun getSyncDescription(): String {
        val offset = getSyncOffsetMs()
        return if (offset == 0) "默认" else "${if (offset > 0) "+" else ""}${offset}ms"
    }
}
