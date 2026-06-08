package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object SearchStatisticsPanelPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.SearchStatisticsPanel.get()
}
