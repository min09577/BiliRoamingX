package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object HomeWidgetCustomPatch {
    private const val TAG = "WidgetCustom"
    @JvmStatic fun isEnabled(): Boolean = Settings.HomeWidgetCustom.get()
    @JvmStatic fun shouldShowWidget(widgetId: String): Boolean = isEnabled()
}
