package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentBluetoothAudioPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentBluetoothAudio.get()
}
