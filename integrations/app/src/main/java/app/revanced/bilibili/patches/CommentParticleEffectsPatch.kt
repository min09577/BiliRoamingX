package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object CommentParticleEffectsPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.CommentParticleEffects.get()
}
