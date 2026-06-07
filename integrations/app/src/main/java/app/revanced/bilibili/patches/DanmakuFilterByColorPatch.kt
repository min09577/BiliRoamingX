package app.revanced.bilibili.patches

import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 按颜色过滤弹幕 — 根据弹幕颜色过滤特定颜色的弹幕
 * 可用于过滤彩色弹幕、广告弹幕等
 */
@Keep
object DanmakuFilterByColorPatch {
    private const val TAG = "DanmakuFilterByColor"

    /**
     * Check if color-based filtering is enabled.
     */
    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.DanmakuFilterByColor.get().isNotEmpty()
    }

    /**
     * Check if a specific color should be filtered.
     * @param colorInt the color as int (0xRRGGBB or 0xAARRGGBB)
     * @return true if the danmaku with this color should be filtered
     */
    @JvmStatic
    fun shouldFilter(colorInt: Int): Boolean {
        if (!isEnabled()) return false

        val filterColors = Settings.DanmakuFilterByColor.get()
        // Check exact match or RGB-only match
        val rgb = colorInt and 0xFFFFFF // strip alpha
        val shouldFilter = filterColors.any { filterColor ->
            val parsed = try {
                filterColor.removePrefix("#").removePrefix("0x").toLong(16).toInt()
            } catch (e: Exception) {
                -1
            }
            parsed == colorInt || (parsed and 0xFFFFFF) == rgb
        }
        if (shouldFilter) {
            Logger.debug { "$TAG: filtered danmaku with color 0x${Integer.toHexString(colorInt)}" }
        }
        return shouldFilter
    }

    /**
     * Check if a color string should be filtered.
     * @param colorStr color as hex string (e.g., "#FF0000", "0x00FF00")
     * @return true if should filter
     */
    @JvmStatic
    fun shouldFilterByString(colorStr: String): Boolean {
        if (!isEnabled()) return false

        val filterColors = Settings.DanmakuFilterByColor.get()
        return filterColors.any { it.equals(colorStr, ignoreCase = true) }
    }

    /**
     * Add a color to the filter list.
     */
    @JvmStatic
    fun addToFilter(colorHex: String) {
        val current = Settings.DanmakuFilterByColor.get().toMutableSet()
        current.add(colorHex)
        Settings.DanmakuFilterByColor.set(current)
        Logger.debug { "$TAG: added color $colorHex to filter, total: ${current.size}" }
    }

    /**
     * Remove a color from the filter list.
     */
    @JvmStatic
    fun removeFromFilter(colorHex: String) {
        val current = Settings.DanmakuFilterByColor.get().toMutableSet()
        current.remove(colorHex)
        Settings.DanmakuFilterByColor.set(current)
        Logger.debug { "$TAG: removed color $colorHex from filter, total: ${current.size}" }
    }

    /**
     * Get all filtered colors.
     */
    @JvmStatic
    fun getFilteredColors(): Set<String> {
        return Settings.DanmakuFilterByColor.get()
    }

    /**
     * Get the count of filtered colors.
     */
    @JvmStatic
    fun getFilterCount(): Int {
        return Settings.DanmakuFilterByColor.get().size
    }
}
