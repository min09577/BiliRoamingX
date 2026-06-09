package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedProceduralGenPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedProceduralGen.get()
}
