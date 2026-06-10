package app.revanced.bilibili.patches

import android.view.View
import android.view.ViewGroup
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object LiveGiftBlockPatch {

    private val GIFT_KEYWORDS = listOf(
        "gift", "Gift", "GIFT",
        "特效", "动画", "礼物",
        "combo", "Combo",
        "anim", "Anim"
    )

    @JvmStatic
    fun hideGiftEffects(view: View?, depth: Int = 0) {
        if (!Settings.LiveGiftBlock.get()) return
        if (view == null || depth > 10) return

        try {
            if (view is ViewGroup) {
                for (i in 0 until view.childCount) {
                    hideGiftEffects(view.getChildAt(i), depth + 1)
                }
            }

            // Check resource name for gift-related views
            if (view.id != View.NO_ID) {
                try {
                    val resName = view.resources.getResourceEntryName(view.id)
                    if (GIFT_KEYWORDS.any { resName.contains(it, ignoreCase = true) }) {
                        view.visibility = View.GONE
                        Logger.debug { "LiveGiftBlock: hidden gift view $resName" }
                    }
                } catch (e: Exception) { Logger.error { "Error in LiveGiftBlockPatch: ${e.message}" } }
            }
        } catch (e: Throwable) {
            Logger.error(e) { "LiveGiftBlock: failed to hide" }
        }
    }

    @JvmStatic
    fun shouldBlockGifts(): Boolean {
        return Settings.LiveGiftBlock.get()
    }
}
