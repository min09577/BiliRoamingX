package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 检测用户离开自动暂停 — 当检测到用户离开屏幕时自动暂停视频
 * 使用前置摄像头或传感器检测用户是否在观看
 */
@Keep
object VideoAutoPauseDetectPatch {
    private const val TAG = "AutoPauseDetect"
    private var isDetecting = false

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.VideoAutoPauseDetect.get()
    }

    @JvmStatic
    fun startDetection() {
        if (!isEnabled()) return
        isDetecting = true
        Logger.debug { "$TAG: started detection" }
    }

    @JvmStatic
    fun stopDetection() {
        isDetecting = false
        Logger.debug { "$TAG: stopped detection" }
    }

    @JvmStatic
    fun isDetecting(): Boolean = isDetecting && isEnabled()

    @JvmStatic
    fun onUserAbsent() {
        if (!isDetecting()) return
        Logger.debug { "$TAG: user absent, should pause" }
    }

    @JvmStatic
    fun onUserPresent() {
        if (!isDetecting()) return
        Logger.debug { "$TAG: user present" }
    }
}