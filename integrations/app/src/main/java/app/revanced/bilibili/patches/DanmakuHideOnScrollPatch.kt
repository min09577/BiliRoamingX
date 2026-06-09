package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object DanmakuHideOnScrollPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.DanmakuHideOnScroll.get()
    @JvmStatic fun shouldHide(isScrolling: Boolean): Boolean = isEnabled() && isScrolling
}
