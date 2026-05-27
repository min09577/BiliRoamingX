package app.revanced.patches.bilibili.video.subtitle.fingerprints

import app.revanced.patcher.fingerprint.MethodFingerprint

object RecordSelectedSubtitleFingerprint : MethodFingerprint(
    strings = listOf(
        "danmaku_subtitle_multi",
        "danmaku_subtitle_lan_main",
    ),
    parameters = listOf("Z", "Z", "Z"),
    returnType = "V",
)
