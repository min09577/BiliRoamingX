package app.revanced.bilibili.patches
import android.widget.TextView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentLineSpacingPatch {
    /**
     * getSpacing
     */
    @JvmStatic fun getSpacing(): Float = Settings.CommentLineSpacing.get().coerceIn(0.8f, 2.0f)
    /**
     * applySpacing
     */
    @JvmStatic fun applySpacing(tv: TextView) { tv.setLineSpacing(0f, getSpacing()) }
}
