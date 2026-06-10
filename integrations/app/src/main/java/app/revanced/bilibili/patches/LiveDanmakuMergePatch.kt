package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object LiveDanmakuMergePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.LiveDanmakuMerge.get()
    /**
     * shouldMerge
     */
    @JvmStatic fun shouldMerge(): Boolean = isEnabled()
}
