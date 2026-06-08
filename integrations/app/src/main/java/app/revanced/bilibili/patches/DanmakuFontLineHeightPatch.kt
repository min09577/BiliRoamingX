package app.revanced.bilibili.patches
import android.graphics.Paint
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuFontLineHeightPatch {
    @JvmStatic fun getLineHeight(): Float = Settings.DanmakuFontLineHeight.get().coerceIn(0.5f, 2.0f)
}
