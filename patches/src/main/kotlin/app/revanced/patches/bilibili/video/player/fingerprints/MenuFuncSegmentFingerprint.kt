package app.revanced.patches.bilibili.video.player.fingerprints

import app.revanced.patcher.fingerprint.MethodFingerprint

object MenuFuncSegmentFingerprint : MethodFingerprint(
    strings = listOf("player.player.full-more.speed.player"),
    parameters = listOf("F"),
    returnType = "V",
)
