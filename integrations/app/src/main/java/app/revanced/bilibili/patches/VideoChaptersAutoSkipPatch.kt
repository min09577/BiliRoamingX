package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings

@Keep
object VideoChaptersAutoSkipPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.VideoChaptersAutoSkip.get()
    
    @JvmStatic fun shouldAutoSkip(): Boolean = isEnabled()
}
