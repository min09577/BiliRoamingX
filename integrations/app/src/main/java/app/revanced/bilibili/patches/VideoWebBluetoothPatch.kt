package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object VideoWebBluetoothPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoWebBluetooth.get()
}
