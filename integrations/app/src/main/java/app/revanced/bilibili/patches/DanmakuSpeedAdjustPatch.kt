package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuSpeedAdjustPatch {
    @JvmStatic fun getSpeedFactor(): Float = Settings.DanmakuSpeedAdjust.get().coerceIn(0.5f, 3.0f)
    @JvmStatic fun adjustDuration(baseMs: Long): Long = (baseMs / getSpeedFactor()).toLong()
}
