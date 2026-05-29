package app.revanced.patches.bilibili.video.detail.patch

import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.annotation.CompatiblePackage
import app.revanced.patcher.patch.annotation.Patch

@Patch(
    name = "Video desc expand",
    description = "视频简介自动展开 + 隐藏浮动按钮",
    compatiblePackages = [
        CompatiblePackage(name = "tv.danmaku.bili"),
        CompatiblePackage(name = "tv.danmaku.bilibilihd"),
        CompatiblePackage(name = "com.bilibili.app.in")
    ]
)
object VideoDescExpandPatch : BytecodePatch() {
    override fun execute(context: BytecodeContext) {
        // Hook ExpandableLayout.onFinishInflate to auto-expand description
        context.findClass("Ltv/danmaku/bili/ui/video/widget/ExpandableLayout;")
            ?.mutableClass?.methods?.find { it.name == "onFinishInflate" }?.run {
                addInstructions(
                    implementation!!.instructions.size - 1, """
                    invoke-static {p0}, Lapp/revanced/bilibili/patches/VideoDescPatch;->onExpandableLayoutInflate(Landroid/view/View;)V
                """.trimIndent()
                )
            }

        // Also hook ExpandableTextView for description text
        context.findClass("Ltv/danmaku/bili/videopage/common/widget/view/ExpandableTextView;")
            ?.mutableClass?.methods?.find { it.name == "onFinishInflate" }?.run {
                addInstructions(
                    implementation!!.instructions.size - 1, """
                    invoke-static {p0}, Lapp/revanced/bilibili/patches/VideoDescPatch;->onExpandableLayoutInflate(Landroid/view/View;)V
                """.trimIndent()
                )
            }
    }
}
