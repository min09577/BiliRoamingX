package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerAccelerometerGesturePatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerAccelerometerGesture.get()
}
