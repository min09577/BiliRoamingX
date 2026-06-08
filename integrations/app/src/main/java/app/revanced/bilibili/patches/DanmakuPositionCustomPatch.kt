package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuPositionCustomPatch {
    const val POS_DEFAULT = 0; const val POS_TOP = 1; const val POS_BOTTOM = 2; const val POS_CENTER = 3
    @JvmStatic fun getPosition(): Int = Settings.DanmakuPositionCustom.get().coerceIn(0, 3)
    @JvmStatic fun isTop(): Boolean = getPosition() == POS_TOP
    @JvmStatic fun isBottom(): Boolean = getPosition() == POS_BOTTOM
    @JvmStatic fun isCenter(): Boolean = getPosition() == POS_CENTER
}
