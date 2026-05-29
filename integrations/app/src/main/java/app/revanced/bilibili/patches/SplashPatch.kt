package app.revanced.bilibili.patches

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.Utils
import app.revanced.bilibili.utils.findView
import java.io.File

object SplashPatch {
    const val SPLASH_IMAGE = "biliroaming_splash"
    const val LOGO_IMAGE = "biliroaming_logo"

    @Keep
    @JvmStatic
    fun getMode(origin: String): String {
        // When purify_splash is enabled, return empty to skip ad splash
        if (Settings.PurifySplash()) {
            Logger.debug { "SplashPatch, purify splash, skip ad, origin mode: $origin" }
            return "skip"
        }
        return if (Settings.FullSplash()) "full" else origin
    }

    @Keep
    @JvmStatic
    fun onBrandSplashFragmentViewCreated(view: View) {
        // When purify_splash is enabled, hide splash and skip to main content
        if (Settings.PurifySplash()) {
            Logger.debug { "SplashPatch, purify splash enabled, hiding splash views" }
            try {
                // Hide all splash views
                val splash = view.findView<ImageView>("brand_splash")
                val fullSplash = view.findView<ImageView>("full_brand_splash")
                val logo = view.findView<ImageView>("brand_logo")
                splash?.alpha = 0f
                fullSplash?.alpha = 0f
                logo?.alpha = 0f
                // Make the root view transparent
                view.alpha = 0f
                // Post a delayed action to finish the splash activity
                view.postDelayed({
                    try {
                        val activity = (view.context as? android.app.Activity)
                        if (activity != null && !activity.isFinishing) {
                            Logger.debug { "SplashPatch, finishing splash activity" }
                            activity.finish()
                        }
                    } catch (e: Exception) {
                        Logger.error(e) { "SplashPatch, failed to finish splash activity" }
                    }
                }, 100L)
            } catch (e: Exception) {
                Logger.error(e) { "SplashPatch, failed to hide splash views" }
            }
            return
        }
        if (Settings.CustomSplash()) {
            val splash = view.findView<ImageView>("brand_splash")
            val fullSplash = view.findView<ImageView>("full_brand_splash")
            val splashImage = File(Utils.getContext().filesDir, SPLASH_IMAGE)
            if (splashImage.isFile) {
                val uri = Uri.fromFile(splashImage)
                splash.setImageURI(uri)
                fullSplash.setImageURI(uri)
            } else {
                splash.alpha = 0f
                fullSplash.alpha = 0f
            }
        }
        if (Settings.CustomSplashLogo()) {
            val logo = view.findView<ImageView>("brand_logo")
            val logoImage = File(Utils.getContext().filesDir, LOGO_IMAGE)
            if (logoImage.isFile) {
                logo.setImageURI(Uri.fromFile(logoImage))
            } else {
                logo.alpha = 0f
            }
        }
    }
}
