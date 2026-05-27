package app.revanced.patches.bilibili.video.player.fingerprints

import app.revanced.patcher.fingerprint.MethodFingerprint

object MenuServiceCreateSpeedFingerprint : MethodFingerprint(
    strings = listOf("MenuService\$createSpeed$2", "theseus-united"),
    parameters = listOf("I"),
    returnType = "V",
)
