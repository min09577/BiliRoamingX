package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerWebTransportPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerWebTransport.get()
}
