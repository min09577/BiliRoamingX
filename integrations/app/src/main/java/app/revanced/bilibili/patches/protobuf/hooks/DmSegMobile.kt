package app.revanced.bilibili.patches.protobuf.hooks

import app.revanced.bilibili.patches.protobuf.MossHook
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.Utils
import app.revanced.bilibili.utils.clearUnknownFields
import app.revanced.bilibili.utils.maybeThailand
import com.bapis.bilibili.community.service.dm.v1.DmColorfulType
import com.bapis.bilibili.community.service.dm.v1.DmSegMobileReply
import com.bapis.bilibili.community.service.dm.v1.DmSegMobileReq
import com.bilibili.lib.moss.api.MossException
import com.google.protobuf.GeneratedMessageLite

object DmSegMobile : MossHook<DmSegMobileReq, DmSegMobileReply>() {
    private val timePointRegex by lazy { Regex("""(?:(?<hours>\d{1,4})[:：])?(?<minutes>\d{1,2})[:：](?<seconds>\d{1,2})""") }

    private var compiledFilterRegex: Regex? = null
    private var lastFilterKeywords: Set<String> = emptySet()

    private fun getFilterRegex(): Regex? {
        val keywords = Settings.DanmakuFilterKeywords()
        if (keywords.isEmpty()) {
            compiledFilterRegex = null
            lastFilterKeywords = emptySet()
            return null
        }
        if (keywords != lastFilterKeywords) {
            lastFilterKeywords = keywords
            compiledFilterRegex = try {
                if (Settings.DanmakuFilterRegexMode()) {
                    Regex(keywords.joinToString("|"), RegexOption.IGNORE_CASE)
                } else {
                    val escaped = keywords.map { Regex.escape(it) }
                    Regex(escaped.joinToString("|"), RegexOption.IGNORE_CASE)
                }
            } catch (e: Exception) {
                Logger.error(e) { "DanmakuFilter, failed to compile regex" }
                null
            }
        }
        return compiledFilterRegex
    }

    override fun shouldHook(req: GeneratedMessageLite<*, *>): Boolean {
        return req is DmSegMobileReq
    }

    override fun hookAfter(
        req: DmSegMobileReq,
        reply: DmSegMobileReply?,
        error: MossException?
    ): DmSegMobileReply? {
        if (reply != null && Settings.UnlockAreaLimit() && Settings.ThailandServer().isNotEmpty()) {
            val epId = req.oid.toString()
            val seasonId = req.pid.toString()
            if (maybeThailand(seasonId, epId))
                reply.clearElems()
        }
        val noColorfulDanmaku = Settings.NoColorfulDanmaku()
        val timeAirborne = Settings.TimeAirborne()
        val filterRegex = getFilterRegex()
        val minLength = Settings.DanmakuFilterMinLength()
        val opacity = Settings.DanmakuOpacity()
        val maxOnScreen = Settings.DanmakuMaxOnScreen()
        val fontSizeScale = Settings.DanmakuFontSizeScale()
        val shouldFilter = filterRegex != null || minLength > 0 || maxOnScreen > 0
        // Step 1: Filter by keyword/length/density
        if (shouldFilter && reply != null) {
            val originalCount = reply.elemsCount
            var filtered = reply.elemsList.filter { elem ->
                val content = elem.content
                if (minLength > 0 && content.length < minLength) return@filter false
                if (filterRegex != null && filterRegex.containsMatchIn(content)) return@filter false
                true
            }
            // Density control: randomly sample to max count
            if (maxOnScreen > 0 && filtered.size > maxOnScreen) {
                val step = filtered.size.toFloat() / maxOnScreen
                filtered = (0 until maxOnScreen).map { i -> filtered[(i * step).toInt()] }
            }
            if (filtered.size < originalCount) {
                reply.clearElems()
                reply.addAllElems(filtered)
                Logger.debug { "DanmakuFilter, filtered ${originalCount - filtered.size}/$originalCount danmakus" }
            }
        }
        // Step 2: Modify opacity and font size
        reply?.elemsList?.forEach { elem ->
            if (noColorfulDanmaku) {
                if (!Utils.isHd())
                    elem.colorful = DmColorfulType.NoneType
                else {
                    elem.clearUnknownFields()
                }
            }
            // Opacity: apply alpha to color (0xAARRGGBB)
            if (opacity in 1..99) {
                val alpha = (opacity * 255 / 100)
                val rgb = elem.color and 0x00FFFFFF
                elem.color = (alpha shl 24) or rgb
            }
            // Font size scale
            if (fontSizeScale > 0f) {
                elem.fontsize = (elem.fontsize * fontSizeScale).toInt().coerceIn(1, 255)
            }
            if (timeAirborne && !elem.action.startsWith("airborne:")) {
                timePointRegex.find(elem.content)?.let {
                    val (_, hours, minutes, seconds) = it.groupValues
                    val totalSeconds = seconds.toInt() + minutes.toInt() * 60 + hours.ifEmpty { "0" }
                        .toInt() * 60 * 60
                    elem.action = "airborne:${totalSeconds * 1000}"
                }
            }
        }
        return super.hookAfter(req, reply, error)
    }
}
