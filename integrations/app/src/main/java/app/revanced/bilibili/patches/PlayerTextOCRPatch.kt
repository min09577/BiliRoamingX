package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object PlayerTextOCRPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.PlayerTextOCR.get()
}
