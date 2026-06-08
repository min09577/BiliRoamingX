package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedFoldableLayoutPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedFoldableLayout.get()
}
