package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object DynamicAutoLikePatch {
    private const val TAG = "DynamicAutoLike"
    @JvmStatic fun isEnabled(): Boolean = Settings.DynamicAutoLike.get()
    @JvmStatic fun shouldAutoLike(): Boolean {
        val like = isEnabled()
        Logger.debug { "$TAG: auto like=$like" }
        return like
    }
}
