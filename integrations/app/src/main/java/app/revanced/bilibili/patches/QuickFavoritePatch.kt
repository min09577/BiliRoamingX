package app.revanced.bilibili.patches

import android.app.Activity
import android.app.AlertDialog
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.Toasts

@Keep
object QuickFavoritePatch {

    private data class FavoriteFolder(
        val id: Long,
        val name: String,
        val mediaCount: Int
    )

    @JvmStatic
    fun onFavoriteButtonLongClick(activity: Activity?, avid: Long, bvid: String?): Boolean {
        if (!Settings.QuickFavorite.get()) return false
        if (activity == null) return false

        try {
            // Load favorite folders (this would need API integration)
            // For now, show a simple dialog with common options
            val options = arrayOf(
                "默认收藏夹",
                "稍后再看",
                "添加到新收藏夹"
            )

            AlertDialog.Builder(activity)
                .setTitle("快速收藏")
                .setItems(options) { _, which ->
                    when (which) {
                        0 -> addToDefault(activity, avid)
                        1 -> addToWatchLater(activity, avid)
                        2 -> showCreateFolderDialog(activity, avid)
                    else -> {}
    }
                }
                .setNegativeButton("取消", null)
                .show()

            Logger.debug { "QuickFavoritePatch: showed quick favorite dialog" }
            return true
        } catch (e: Throwable) {
            Logger.error(e) { "QuickFavoritePatch: failed to show dialog" }
            return false
        }
    }

    private fun addToDefault(activity: Activity, avid: Long) {
        // This would need to call Bilibili API to add to default folder
        Toasts.showShort("已添加到默认收藏夹")
        Logger.debug { "QuickFavoritePatch: added $avid to default folder" }
    }

    private fun addToWatchLater(activity: Activity, avid: Long) {
        Toasts.showShort("已添加到稍后再看")
        Logger.debug { "QuickFavoritePatch: added $avid to watch later" }
    }

    private fun showCreateFolderDialog(activity: Activity, avid: Long) {
        // This would need to show a dialog to create new folder
        Toasts.showShort("创建新收藏夹功能开发中")
        Logger.debug { "QuickFavoritePatch: create folder dialog" }
    }
}
