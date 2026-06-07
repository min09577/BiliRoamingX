package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object LiveAutoReconnectPatch {

    private var reconnectCount = 0
    private const val MAX_RECONNECT = 5

    @JvmStatic
    fun shouldReconnect(): Boolean {
        if (!Settings.LiveAutoReconnect.get()) return false
        if (reconnectCount >= MAX_RECONNECT) {
            Logger.debug { "LiveAutoReconnect: max reconnect attempts reached" }
            return false
        }
        reconnectCount++
        Logger.debug { "LiveAutoReconnect: attempting reconnect $reconnectCount" }
        return true
    }

    @JvmStatic
    fun onConnected() {
        reconnectCount = 0
        Logger.debug { "LiveAutoReconnect: connected, reset counter" }
    }

    @JvmStatic
    fun onDisconnected() {
        Logger.debug { "LiveAutoReconnect: disconnected" }
    }

    @JvmStatic
    fun reset() {
        reconnectCount = 0
    }

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.LiveAutoReconnect.get()
    }
}
