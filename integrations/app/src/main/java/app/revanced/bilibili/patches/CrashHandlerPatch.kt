package app.revanced.bilibili.patches

import android.system.Os
import androidx.annotation.Keep
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.Utils

object CrashHandlerPatch {
    @Keep
    @JvmStatic
    fun onCrash(thread: Thread, error: Throwable) {
        // Check if this is the known DmAdvert bug in 8.95.0
        val isDmAdvertBug = error is ClassNotFoundException &&
            error.message?.contains("DmAdvert") == true
        
        if (isDmAdvertBug) {
            // Log but don't crash - this is a known bilibili bug
            Logger.error(error) {
                "Known 8.95.0 bug: DmAdvert class missing, ignoring crash"
            }
            return
        }
        
        Logger.error(error) {
            "FATAL, crashed, pid: ${Os.getpid()}, tid: ${thread.id}, pname: ${Utils.currentProcessName()}, tname: ${thread.name}, error: "
        }
    }
}
