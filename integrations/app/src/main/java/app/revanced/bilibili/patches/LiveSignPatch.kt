package app.revanced.bilibili.patches

import android.app.Activity
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.Keep
import app.revanced.bilibili.account.Accounts
import app.revanced.bilibili.http.HttpClient
import app.revanced.bilibili.http.RequestBody
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.Toasts
import app.revanced.bilibili.utils.Utils

@Keep
object LiveSignPatch {
    @Volatile
    private var signedToday = false
    private var lastSignTime = 0L

    @JvmStatic
    fun onLiveRoomResumed(activity: Activity) {
        if (!Settings.LiveAutoSign()) return
        if (signedToday) return
        val now = System.currentTimeMillis()
        if (now - lastSignTime < 5 * 60 * 1000) return
        lastSignTime = now
        Utils.async(::doSign)
    }

    private fun doSign() {
        try {
            val sessData = Accounts.cookieSESSDATA
            val csrf = Accounts.cookieBiliJct
            if (sessData.isEmpty() || csrf.isEmpty()) {
                Logger.debug { "LiveSignPatch: not logged in, skip sign" }
            }

            val response = HttpClient.post(
                "https://api.live.bilibili.com/xlive/general-interface/v1/Anchor/AnchorCheck",
                RequestBody.form("csrf" to csrf, "csrf_token" to csrf),
                headers = mapOf("Cookie" to "SESSDATA=$sessData")
            )

            if (response != null) {
                val json = response.json()
                if (json != null) {
                    val code = json.optInt("code", -1)
                    Logger.debug { "LiveSignPatch: sign response code=$code, msg=${json.optString("message", "")}" }
                    when (code) {
                        0 -> {
                            signedToday = true
                            val data = json.optJSONObject("data")
                            val text = data?.optString("text", "").orEmpty()
                            val msg = "直播间签到成功！${if (text.isEmpty()) "" else " $text"}"
                            Toasts.show(msg, Toast.LENGTH_SHORT, Gravity.CENTER)
                        }
                        1011040 -> {
                            signedToday = true
                            Logger.debug { "LiveSignPatch: already signed today" }
                        }
                    }
                }
            }
        } catch (e: Throwable) {
            Logger.error(e) { "LiveSignPatch: sign failed" }
        }
    }
}
