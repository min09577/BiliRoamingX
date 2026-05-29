package app.revanced.bilibili.patches.protobuf.hooks

import android.content.pm.ActivityInfo
import app.revanced.bilibili.account.Accounts
import app.revanced.bilibili.meta.VideoInfo
import app.revanced.bilibili.patches.TrialQualityPatch
import app.revanced.bilibili.patches.VideoQualityPatch
import app.revanced.bilibili.patches.main.ApplicationDelegate
import app.revanced.bilibili.patches.main.VideoInfoHolder
import app.revanced.bilibili.patches.protobuf.MossHook
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.Toasts
import app.revanced.bilibili.utils.Utils
import com.bapis.bilibili.app.playurl.v1.PlayAbilityConf
import com.bapis.bilibili.app.playurl.v1.PlayViewReply
import com.bapis.bilibili.app.playurl.v1.PlayViewReq
import com.bapis.bilibili.app.viewunite.v1.ViewReply
import com.bilibili.lib.moss.api.MossException
import com.bilibili.video.videodetail.VideoDetailsActivity
import com.google.protobuf.GeneratedMessageLite

object PlayURLPlayViewUGC : MossHook<PlayViewReq, PlayViewReply>() {
    private val codecNames = mapOf(
        7 to "AVC/H.264",
        12 to "HEVC/H.265",
        13 to "AV1"
    )

    private val qualityNames = mapOf(
        127 to "超高清 8K",
        126 to "杜比视界",
        125 to "HDR",
        120 to "超清 4K",
        116 to "高帧率 1080P60",
        112 to "高码率 1080P+",
        80 to "高清 1080P",
        74 to "高帧率 720P60",
        64 to "高清 720P",
        32 to "清晰 480P",
        16 to "流畅 360P"
    )

    private var lastCodecInfoCid = 0L

    override fun shouldHook(req: GeneratedMessageLite<*, *>): Boolean {
        return req is PlayViewReq
    }

    override fun hookBefore(req: PlayViewReq): Any? {
        VideoQualityPatch.unlockLimit(req)
        return null
    }

    override fun hookAfter(
        req: PlayViewReq,
        reply: PlayViewReply?,
        error: MossException?
    ): PlayViewReply? {
        if (reply != null) {
            if (req.download == 0) {
                VideoInfoHolder.updateCurrent { videoInfo ->
                    videoInfo?.apply { cid = req.cid } ?: VideoInfo(req.cid, null)
                }
            }
            if (Settings.RememberLosslessSetting())
                reply.playConf.takeIf(PlayAbilityConf::hasLossLessConf)?.run {
                    lossLessConf.confValue.switchVal = Settings.LosslessEnabled()
                }
            if (Settings.UnlockPlayLimit()) {
                if (reply.playArc.backgroundPlayConf.disabled) {
                    reply.playConf.takeIf(PlayAbilityConf::hasBackgroundPlayConf)?.run {
                        backgroundPlayConf.confValue.switchVal = Settings.BgPlayingEnabled()
                    }
                }
                reply.playArc.run {
                    arrayOf(castConf, backgroundPlayConf, smallWindowConf).forEach {
                        it.isSupport = true
                        it.disabled = false
                    }
                }
            }
            if (req.download < 1 && !Accounts.isEffectiveVip
                && (Settings.TrialVipQuality() || Settings.UnlimitedTrialQuality())
            ) TrialQualityPatch.makeVipFree(reply)
            // Show codec info toast
            if (Settings.ShowCodecInfo() && req.download == 0 && req.cid != lastCodecInfoCid) {
                lastCodecInfoCid = req.cid
                showCodecInfo(reply)
            }
            if (Utils.isHd() && Settings.NotLockOrientation()) {
                val dashVideo = reply.videoInfo.streamListList.firstNotNullOfOrNull {
                    if (it.hasDashVideo()) it.dashVideo else null
                }
                if (dashVideo != null) {
                    val width = dashVideo.width
                    val height = dashVideo.height
                    Utils.runOnMainThread {
                        val topActivity = ApplicationDelegate.getTopActivity()
                        if (topActivity is VideoDetailsActivity) {
                            val orientation = if (height > width) {
                                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                            } else {
                                ActivityInfo.SCREEN_ORIENTATION_BEHIND
                            }
                            topActivity.requestedOrientation = orientation
                        }
                    }
                }
            }
        }
        return super.hookAfter(req, reply, error)
    }

    private fun showCodecInfo(reply: PlayViewReply) {
        try {
            val videoInfo = reply.videoInfo ?: return
            val quality = videoInfo.quality
            val qualityName = qualityNames[quality] ?: "未知($quality)"
            val stream = videoInfo.streamListList.firstOrNull { it.hasDashVideo() } ?: return
            val dashVideo = stream.dashVideo
            val codecId = dashVideo.codecid
            val codecName = codecNames[codecId] ?: "未知($codecId)"
            val bitrate = dashVideo.bandwidth / 1000 // kbps
            val width = dashVideo.width
            val height = dashVideo.height
            val info = "$qualityName | $codecName | ${width}x${height} | ${bitrate}kbps"
            Logger.debug { "CodecInfo: $info" }
            Toasts.showShort(info)
            // Show AV/BV info
            if (Settings.ShowVideoAvBvId()) {
                val view = VideoInfoHolder.current?.view
                val avBvInfo = when (view) {
                    is com.bapis.bilibili.app.view.v1.ViewReply -> "av${view.arc.aid}"
                    is ViewReply -> if (view.hasArc()) "av${view.arc.aid} / ${view.arc.bvid}" else null
                    else -> null
                }
                if (avBvInfo != null) {
                    Toasts.showShort(avBvInfo)
                }
            }
        } catch (e: Exception) {
            Logger.error(e) { "CodecInfo, failed to show codec info" }
        }
    }
}
