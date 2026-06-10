package app.revanced.bilibili.patches

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger

/**
 * 弹幕发送动画 — 新发送的弹幕带有入场动画效果
 * 支持淡入、滑入、缩放等动画
 */
@Keep
object DanmakuSendAnimationPatch {
    private const val TAG = "DanmakuSendAnimation"
    // Animation duration in ms
    private const val ANIM_DURATION = 500L

    /**
     * Check if send animation is enabled.
     */
    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.DanmakuSendAnimation.get()
    }

    /**
     * Hook target: apply entrance animation to newly sent danmaku.
     * Called when a new danmaku view is added to the container.
     */
    @JvmStatic
    fun applyEntranceAnimation(view: View) {
        if (!Settings.DanmakuSendAnimation.get()) return

        try {
            // Fade in + slide from right
            view.alpha = 0f
            view.translationX = 100f

            val fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
            val slideIn = ObjectAnimator.ofFloat(view, "translationX", 100f, 0f)

            fadeIn.duration = ANIM_DURATION
            slideIn.duration = ANIM_DURATION
            fadeIn.interpolator = AccelerateInterpolator()
            slideIn.interpolator = AccelerateInterpolator()

            fadeIn.start()
            slideIn.start()

            Logger.debug { "$TAG: entrance animation applied" }
        } catch (e: Throwable) {
            Logger.debug { "$TAG: failed to apply animation: $e" }
        }
    }

    /**
     * Hook target: apply scale animation for emphasis.
     * Called for special danmaku (e.g., own messages).
     */
    @JvmStatic
    fun applyScaleAnimation(view: View) {
        if (!Settings.DanmakuSendAnimation.get()) return

        try {
            view.scaleX = 0.5f
            view.scaleY = 0.5f

            val scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1.2f, 1.0f)
            val scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1.2f, 1.0f)

            scaleX.duration = ANIM_DURATION
            scaleY.duration = ANIM_DURATION

            scaleX.start()
            scaleY.start()

            Logger.debug { "$TAG: scale animation applied" }
        } catch (e: Throwable) {
            Logger.debug { "$TAG: failed to apply scale animation: $e" }
        }
    }

    /**
     * Hook target: apply exit animation when danmaku leaves screen.
     */
    @JvmStatic
    fun applyExitAnimation(view: View, onEnd: Runnable? = null) {
        if (!Settings.DanmakuSendAnimation.get()) {
            onEnd?.run()
        }

        try {
            val fadeOut = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
            fadeOut.duration = 300L
            fadeOut.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    onEnd?.run()
                }
            })
            fadeOut.start()

            Logger.debug { "$TAG: exit animation applied" }
        } catch (e: Throwable) {
            Logger.debug { "$TAG: failed to apply exit animation: $e" }
            onEnd?.run()
        }
    }
}
