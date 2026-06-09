package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object HomeFeedBluetoothScanPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeFeedBluetoothScan.get()
}
