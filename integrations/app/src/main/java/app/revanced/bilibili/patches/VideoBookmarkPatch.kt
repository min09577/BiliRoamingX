package app.revanced.bilibili.patches

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.Utils
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

@Keep
object VideoBookmarkPatch {
    private const val PREFS_NAME = "video_bookmarks"
    private const val KEY_BOOKMARKS = "bookmarks_data"

    data class Bookmark(
        val bvid: String,
        val cid: Long,
        val timestamp: Long, // in milliseconds
        val note: String,
        val createdAt: Long = System.currentTimeMillis()
    )

    /**
     * Add a bookmark at the current playback position.
     */
    @JvmStatic
    fun addBookmark(context: Context, bvid: String, cid: Long, currentPositionMs: Long, note: String): Boolean {
        if (!Settings.VideoBookmark.get()) return false
        try {
            val bookmark = Bookmark(bvid, cid, currentPositionMs, note)
            saveBookmark(context, bookmark)
            Logger.debug { "VideoBookmarkPatch: added bookmark at ${formatTime(currentPositionMs)}" }
            Utils.runOnMainThread { Toast.makeText(context, "书签已添加: ${formatTime(currentPositionMs)}", Toast.LENGTH_SHORT).show() }
            return true
        } catch (e: Throwable) {
            Logger.error(e) { "VideoBookmarkPatch: failed to add bookmark" }
            return false
        }
    }

    /**
     * Get all bookmarks for a specific video.
     */
    @JvmStatic
    fun getBookmarks(context: Context, bvid: String, cid: Long): List<Bookmark> {
        if (!Settings.VideoBookmark.get()) return emptyList()
        try {
            val allBookmarks = loadAllBookmarks(context)
            return allBookmarks.filter { it.bvid == bvid && it.cid == cid }
                .sortedBy { it.timestamp }
        } catch (e: Throwable) {
            Logger.error(e) { "VideoBookmarkPatch: failed to get bookmarks" }
            return emptyList()
        }
    }

    /**
     * Delete a bookmark.
     */
    @JvmStatic
    fun deleteBookmark(context: Context, bvid: String, cid: Long, timestamp: Long): Boolean {
        try {
            val allBookmarks = loadAllBookmarks(context).toMutableList()
            val removed = allBookmarks.removeAll { it.bvid == bvid && it.cid == cid && it.timestamp == timestamp }
            if (removed) {
                saveAllBookmarks(context, allBookmarks)
                Logger.debug { "VideoBookmarkPatch: deleted bookmark at ${formatTime(timestamp)}" }
            }
            return removed
        } catch (e: Throwable) {
            Logger.error(e) { "VideoBookmarkPatch: failed to delete bookmark" }
            return false
        }
    }

    /**
     * Show bookmark dialog for adding a new bookmark.
     */
    @JvmStatic
    fun showAddBookmarkDialog(activity: Activity, bvid: String, cid: Long, currentPositionMs: Long) {
        if (!Settings.VideoBookmark.get()) return
        try {
            val dialog = android.app.AlertDialog.Builder(activity)
                .setTitle("添加书签")
                .setView(createBookmarkInputView(activity, currentPositionMs))
                .setPositiveButton("保存") { d, _ ->
                    val editText = (d as android.app.AlertDialog).findViewById<EditText>(android.R.id.input)
                    val note = editText?.text?.toString() ?: ""
                    addBookmark(activity, bvid, cid, currentPositionMs, if (note.isEmpty()) "书签 ${formatTime(currentPositionMs)}" else note)
                }
                .setNegativeButton("取消", null)
                .create()
            dialog.show()
        } catch (e: Throwable) {
            Logger.error(e) { "VideoBookmarkPatch: failed to show dialog" }
        }
    }

    /**
     * Show bookmarks list dialog for a video.
     */
    @JvmStatic
    fun showBookmarksListDialog(activity: Activity, bvid: String, cid: Long, onJump: (Long) -> Unit) {
        if (!Settings.VideoBookmark.get()) return
        try {
            val bookmarks = getBookmarks(activity, bvid, cid)
            if (bookmarks.isEmpty()) {
                Toast.makeText(activity, "暂无书签", Toast.LENGTH_SHORT).show()
            }

            val adapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1)
            bookmarks.forEach { bm ->
                adapter.add("${formatTime(bm.timestamp)} - ${bm.note}")
            }

            android.app.AlertDialog.Builder(activity)
                .setTitle("视频书签 (${bookmarks.size})")
                .setAdapter(adapter) { _, which ->
                    onJump(bookmarks[which].timestamp)
                }
                .setNegativeButton("关闭", null)
                .setNeutralButton("管理") { _, _ ->
                    showManageBookmarksDialog(activity, bvid, cid, onJump)
                }
                .show()
        } catch (e: Throwable) {
            Logger.error(e) { "VideoBookmarkPatch: failed to show list" }
        }
    }

    private fun showManageBookmarksDialog(activity: Activity, bvid: String, cid: Long, onJump: (Long) -> Unit) {
        val bookmarks = getBookmarks(activity, bvid, cid).toMutableList()
        if (bookmarks.isEmpty()) return

        val adapter = object : ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val textView = view.findViewById<TextView>(android.R.id.text1)
                textView.text = "${formatTime(bookmarks[position].timestamp)} - ${bookmarks[position].note}"
                return view
            }
        }

        android.app.AlertDialog.Builder(activity)
            .setTitle("管理书签")
            .setAdapter(adapter) { _, which ->
                val bm = bookmarks[which]
                android.app.AlertDialog.Builder(activity)
                    .setTitle("操作")
                    .setItems(arrayOf("跳转", "编辑", "删除")) { _, action ->
                        when (action) {
                            0 -> onJump(bm.timestamp)
                            1 -> showEditBookmarkDialog(activity, bm, bvid, cid, onJump)
                            2 -> {
                                deleteBookmark(activity, bvid, cid, bm.timestamp)
                                Toast.makeText(activity, "已删除", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    .show()
            }
            .setNegativeButton("返回") { _, _ -> showBookmarksListDialog(activity, bvid, cid, onJump) }
            .show()
    }

    private fun showEditBookmarkDialog(activity: Activity, bookmark: Bookmark, bvid: String, cid: Long, onJump: (Long) -> Unit) {
        val editText = EditText(activity).apply {
            setText(bookmark.note)
            setPadding(50, 20, 50, 20)
        }
        android.app.AlertDialog.Builder(activity)
            .setTitle("编辑书签 - ${formatTime(bookmark.timestamp)}")
            .setView(editText)
            .setPositiveButton("保存") { _, _ ->
                deleteBookmark(activity, bvid, cid, bookmark.timestamp)
                addBookmark(activity, bvid, cid, bookmark.timestamp, editText.text.toString())
                Toast.makeText(activity, "已更新", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("取消", null)
            .show()
    }

    private fun createBookmarkInputView(context: Context, timestampMs: Long): View {
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 20, 50, 20)
        }
        val timeText = TextView(context).apply {
            text = "时间: ${formatTime(timestampMs)}"
            textSize = 16f
            setPadding(0, 0, 0, 20)
        }
        val editText = EditText(context).apply {
            id = android.R.id.input
            hint = "输入备注（可选）"
        }
        layout.addView(timeText)
        layout.addView(editText)
        return layout
    }

    private fun formatTime(ms: Long): String {
        val totalSeconds = ms / 1000
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60
        return if (hours > 0) {
            String.format("%d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format("%02d:%02d", minutes, seconds)
        }
    }

    private fun saveBookmark(context: Context, bookmark: Bookmark) {
        val allBookmarks = loadAllBookmarks(context).toMutableList()
        // Avoid duplicates at the same timestamp
        allBookmarks.removeAll { it.bvid == bookmark.bvid && it.cid == bookmark.cid && it.timestamp == bookmark.timestamp }
        allBookmarks.add(bookmark)
        saveAllBookmarks(context, allBookmarks)
    }

    private fun loadAllBookmarks(context: Context): List<Bookmark> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_BOOKMARKS, null) ?: return emptyList()
        return try {
            val array = JSONArray(json)
            (0 until array.length()).map { i ->
                val obj = array.getJSONObject(i)
                Bookmark(
                    bvid = obj.getString("bvid"),
                    cid = obj.getLong("cid"),
                    timestamp = obj.getLong("timestamp"),
                    note = obj.getString("note"),
                    createdAt = obj.getLong("createdAt")
                )
            }
        } catch (e: Throwable) {
            emptyList()
        }
    }

    private fun saveAllBookmarks(context: Context, bookmarks: List<Bookmark>) {
        val array = JSONArray()
        bookmarks.forEach { bm ->
            array.put(JSONObject().apply {
                put("bvid", bm.bvid)
                put("cid", bm.cid)
                put("timestamp", bm.timestamp)
                put("note", bm.note)
                put("createdAt", bm.createdAt)
            })
        }
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_BOOKMARKS, array.toString())
            .apply()
    }
}
