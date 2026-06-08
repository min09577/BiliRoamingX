package app.revanced.bilibili.patches
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
@Keep
object LiveDanmakuMergeSimilarPatch {
    @JvmStatic fun isEnabled(): Boolean = Settings.LiveDanmakuMergeSimilar.get()
    @JvmStatic fun isSimilar(a: String, b: String): Boolean {
        if (!isEnabled()) return false
        if (a == b) return true
        val similarity = a.zip(b).count { (x, y) -> x == y }.toFloat() / maxOf(a.length, b.length)
        return similarity > 0.8f
    }
}
