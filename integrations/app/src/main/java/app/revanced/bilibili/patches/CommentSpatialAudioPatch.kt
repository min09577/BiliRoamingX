package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentSpatialAudioPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentSpatialAudio.get()
}
