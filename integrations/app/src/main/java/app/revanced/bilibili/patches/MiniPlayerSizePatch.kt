package app.revanced.bilibili.patches

import android.view.View
import android.view.ViewGroup
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 迷你播放器大小自定义 — 调整迷你播放器（小窗）的尺寸
 * 0=默认, 1=小, 2=中, 3=大
 */
@Keep
object MiniPlayerSizePatch {
    private const val TAG = "MiniPlayerSize"
    const val SIZE_DEFAULT = 0
    const val SIZE_SMALL = 1
    const val SIZE_MEDIUM = 2
    const val SIZE_LARGE = 3

    // Scale factors relative to default
    private val SCALE_FACTORS = floatArrayOf(1.0f, 0.7f, 1.0f, 1.4f)

    @JvmStatic
    fun getSizePreset(): Int {
        return Settings.MiniPlayerSize.get().coerceIn(0, 3)
    }

    @JvmStatic
    fun getScaleFactor(): Float {
        return SCALE_FACTORS[getSizePreset()]
    }

    @JvmStatic
    fun applySize(miniPlayer: View?) {
        if (miniPlayer == null) return
        val scale = getScaleFactor()
        if (scale == 1.0f) return

        val params = miniPlayer.layoutParams
        if (params is ViewGroup.MarginLayoutParams) {
            params.width = (params.width * scale).toInt()
            params.height = (params.height * scale).toInt()
            miniPlayer.layoutParams = params
            Logger.debug { "$TAG: applied scale=$scale, w=${params.width}, h=${params.height}" }
        }
    }

    @JvmStatic
    fun getSizeDescription(): String {
        return when (getSizePreset()) {
            SIZE_SMALL -> "小"
            SIZE_MEDIUM -> "中"
            SIZE_LARGE -> "大"
            else -> "默认"
        }
    }
}