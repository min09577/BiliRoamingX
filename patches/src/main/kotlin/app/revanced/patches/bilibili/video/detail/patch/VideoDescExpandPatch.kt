package app.revanced.patches.bilibili.video.detail.patch

import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.annotation.CompatiblePackage
import app.revanced.patcher.patch.annotation.Patch

@Patch(
    name = "Video desc expand",
    description = "视频简介自动展开",
    compatiblePackages = [
        CompatiblePackage(name = "tv.danmaku.bili"),
        CompatiblePackage(name = "tv.danmaku.bilibilihd"),
        CompatiblePackage(name = "com.bilibili.app.in")
    ]
)
object VideoDescExpandPatch : BytecodePatch() {
    override fun execute(context: BytecodeContext) {
        // No-op for now — just registers the patch
        // Actual implementation pending register-safe injection point
    }
}
