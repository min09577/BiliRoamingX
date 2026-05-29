package app.revanced.bilibili.patches.okhttp.hooks

import app.revanced.bilibili.patches.okhttp.ApiHook
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.toJSONObject
import org.json.JSONArray

/**
 * Block related games (相关游戏) section in video detail page.
 * Intercepts /x/v2/view response and removes game-related recommend cards.
 */
object RelatedGames : ApiHook() {
    override fun shouldHook(url: String, status: Int): Boolean {
        return Settings.BlockRelatedGames()
                && status.isOk
                && url.contains("/x/v2/view?")
    }

    override fun hook(url: String, status: Int, request: String, response: String): String {
        Logger.debug { "RelatedGames, hooking view API: $url" }
        try {
            val jo = response.toJSONObject()
            if (jo.optInt("code") == 0) {
                jo.optJSONObject("data")?.let { data ->
                    // Remove related games from cards
                    data.optJSONArray("card")?.let { cards ->
                        val filtered = JSONArray()
                        for (i in 0 until cards.length()) {
                            val card = cards.optJSONObject(i)
                            val uri = card?.optString("uri").orEmpty()
                            // Filter out game-related cards
                            if (!uri.contains("game") && !uri.contains("Game")
                                && !uri.contains("game_center")
                                && card?.optString("card_type") != "game"
                            ) {
                                filtered.put(card)
                            }
                        }
                        data.put("card", filtered)
                    }

                    // Remove game section from relates
                    data.optJSONArray("relates")?.let { relates ->
                        val filtered = JSONArray()
                        for (i in 0 until relates.length()) {
                            val relate = relates.optJSONObject(i)
                            val goto = relate?.optString("goto").orEmpty()
                            val uri = relate?.optString("uri").orEmpty()
                            // Filter out game-related items
                            if (goto != "game" && !uri.contains("game_center")
                                && relate?.optInt("cm_mark", 0) != 1
                            ) {
                                filtered.put(relate)
                            }
                        }
                        data.put("relates", filtered)
                    }

                    // Remove activity cards (game promotions)
                    data.optJSONArray("activity_card")?.let { activities ->
                        val filtered = JSONArray()
                        for (i in 0 until activities.length()) {
                            val activity = activities.optJSONObject(i)
                            val type = activity?.optString("type").orEmpty()
                            if (type != "game") {
                                filtered.put(activity)
                            }
                        }
                        data.put("activity_card", filtered)
                    }
                }
            }
            Logger.debug { "RelatedGames, removed related games data" }
            return jo.toString()
        } catch (e: Exception) {
            Logger.error(e) { "RelatedGames, failed to hook view API" }
            return response
        }
    }
}
