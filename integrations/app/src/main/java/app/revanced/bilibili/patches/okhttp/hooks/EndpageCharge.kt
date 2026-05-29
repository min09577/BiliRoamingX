package app.revanced.bilibili.patches.okhttp.hooks

import app.revanced.bilibili.patches.okhttp.ApiHook
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.toJSONObject
import org.json.JSONArray
import org.json.JSONObject

/**
 * Block charging endpage (充电鸣谢) popup at video end.
 * Intercepts /x/v2/view response and removes endpage/charge_section data.
 */
object EndpageCharge : ApiHook() {
    override fun shouldHook(url: String, status: Int): Boolean {
        return Settings.BlockEndpageCharge()
                && status.isOk
                && (url.contains("/x/v2/view?")
                        || url.contains("/x/v2/view?"))
    }

    override fun hook(url: String, status: Int, request: String, response: String): String {
        Logger.debug { "EndpageCharge, hooking view API: $url" }
        try {
            val jo = response.toJSONObject()
            if (jo.optInt("code") == 0) {
                jo.optJSONObject("data")?.let { data ->
                    // Remove endpage charge section
                    data.optJSONObject("endpage")?.let { endpage ->
                        endpage.put("button", JSONObject.NULL)
                        endpage.put("title", "")
                        endpage.put("related", JSONArray())
                    }
                    // Remove charge/item section if present
                    data.optJSONObject("charge")?.let { charge ->
                        charge.put("charge_list", JSONArray())
                        charge.put("charge_msg", "")
                    }
                    // Remove elec (充电) related
                    data.remove("elec")
                }
            }
            Logger.debug { "EndpageCharge, removed charge endpage data" }
            return jo.toString()
        } catch (e: Exception) {
            Logger.error(e) { "EndpageCharge, failed to hook view API" }
            return response
        }
    }
}
