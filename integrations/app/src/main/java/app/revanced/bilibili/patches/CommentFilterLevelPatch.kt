package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

@Keep
object CommentFilterLevelPatch {

    @JvmStatic
    fun shouldShowComment(userLevel: Int): Boolean {
        val minLevel = Settings.CommentFilterLevel.get()
        if (minLevel <= 0) return true

        val show = userLevel >= minLevel
        if (!show) {
            Logger.debug { "CommentFilterLevel: filtered comment from level $userLevel (min: $minLevel)" }
        }
        return show
    }

    @JvmStatic
    fun getMinLevel(): Int {
        return Settings.CommentFilterLevel.get()
    }
}
