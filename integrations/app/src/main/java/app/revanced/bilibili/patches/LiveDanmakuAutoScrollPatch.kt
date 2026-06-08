package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object LiveDanmakuAutoScrollPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.LiveDanmakuAutoScroll.get()
}
