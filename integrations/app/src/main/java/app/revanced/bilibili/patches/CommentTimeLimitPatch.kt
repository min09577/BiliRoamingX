package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object CommentTimeLimitPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentTimeLimit.get()
    
    @JvmStatic fun shouldLimit(): Boolean = isEnabled()
}
