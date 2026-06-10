package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object DanmakuAreaTopPatch {

    @JvmStatic
    fun shouldAllowTopArea(): Boolean {
        return Settings.DanmakuAreaTop.get()
    }

    @JvmStatic
    fun getMaxTopArea(): Float {
        // Return percentage of screen height for top danmaku area
        return if (Settings.DanmakuAreaTop.get()) 0.3f else 0f
    }
}
