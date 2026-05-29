package app.revanced.bilibili.patches.okhttp.hooks

import app.revanced.bilibili.patches.okhttp.ApiHook
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.Utils
import app.revanced.bilibili.utils.toJSONObject
import org.json.JSONArray

object SplashAd : ApiHook() {
    override fun shouldHook(url: String, status: Int): Boolean {
        return status.isOk && Settings.PurifySplash()
                && (url.contains("/x/splash/brand/list")
                || url.contains("/x/splash/show")
                || url.contains("/x/splash/event")
                || url.contains("splash_list")
                || url.contains("brand_splash"))
    }

    override fun hook(url: String, status: Int, request: String, response: String): String {
        Logger.debug { "SplashAd, hooking splash API: $url" }
        try {
            val jo = response.toJSONObject()
            if (jo.optInt("code") == 0) {
                jo.optJSONObject("data")?.run {
                    // Clear splash ad list
                    put("list", JSONArray())
                    put("splash_list", JSONArray())
                    put("brand_list", JSONArray())
                    put("event_list", JSONArray())
                    // Set max show count to 0
                    put("max_time", 0)
                    put("min_interval", 0)
                    // Disable auto-show
                    put("auto_open", 0)
                }
                // Also handle top-level data arrays
                if (jo.has("data") && jo.get("data") is JSONArray) {
                    jo.put("data", JSONArray())
                }
            }
            Logger.debug { "SplashAd, cleared splash ad data" }
            return jo.toString()
        } catch (e: Exception) {
            Logger.error(e) { "SplashAd, failed to hook splash API" }
            return response
        }
    }
}
