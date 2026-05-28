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
        // The error may be ClassNotFoundException or NoClassDefFoundError
        // and may be wrapped in CoroutinesInternalError
        val isDmAdvertBug = hasDmAdvertCause(error)
        
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
    
    private fun hasDmAdvertCause(error: Throwable): Boolean {
        var current: Throwable? = error
        val visited = mutableSetOf<Throwable>()
        while (current != null && current !in visited) {
            visited.add(current)
            // Check for both ClassNotFoundException and NoClassDefFoundError
            if ((current is ClassNotFoundException || current is NoClassDefFoundError) && 
                current.message?.contains("DmAdvert") == true) {
                return true
            }
            current = current.cause
        }
        return false
    }
}
