package app.revanced.bilibili.patches

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.Toasts
import app.revanced.bilibili.utils.Utils
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

@Keep
object VideoScreenshotPatch {
    private var lastScreenshotTime = 0L
    private const val DEBOUNCE_MS = 1000L // Prevent rapid screenshots

    /**
     * Take a screenshot of the current video frame.
     * This should be called from the player context.
     */
    @JvmStatic
    fun takeScreenshot(context: Context, player: Any?) {
        if (!Settings.VideoScreenshot.get()) return

        // Debounce
        val now = System.currentTimeMillis()
        if (now - lastScreenshotTime < DEBOUNCE_MS) return
        lastScreenshotTime = now

        try {
            // Get the video view from the player
            val videoView = getVideoView(player) ?: run {
                Toasts.showShort("无法获取视频画面")
                return
            }

            // Create bitmap from video view
            val bitmap = Bitmap.createBitmap(videoView.width, videoView.height, Bitmap.Config.ARGB_8888)
            val canvas = android.graphics.Canvas(bitmap)
            videoView.draw(canvas)

            // Save bitmap
            val savedUri = saveBitmap(context, bitmap)
            if (savedUri != null) {
                Toasts.showShort("截图已保存")
                Logger.debug { "VideoScreenshotPatch: screenshot saved to $savedUri" }

                // Optionally share
                if (Settings.VideoScreenshotShare.get()) {
                    shareScreenshot(context, savedUri)
                }
            } else {
                Toasts.showShort("截图保存失败")
            }

            bitmap.recycle()
        } catch (e: Throwable) {
            Logger.error(e) { "VideoScreenshotPatch: failed to take screenshot" }
            Toasts.showShort("截图失败: ${e.message}")
        }
    }

    /**
     * Get the video view from the player object.
     */
    private fun getVideoView(player: Any?): android.view.View? {
        if (player == null) return null
        try {
            // Try to get the video view through reflection
            val playerClass = player.javaClass
            val videoViewField = playerClass.declaredFields.find {
                it.type.name.contains("VideoView") || it.type.name.contains("SurfaceView") || it.type.name.contains("TextureView")
            }
            if (videoViewField != null) {
                videoViewField.isAccessible = true
                return videoViewField.get(player) as? android.view.View
            }

            // Try to find through methods
            val getVideoViewMethod = playerClass.methods.find {
                it.name.contains("getVideoView") || it.name.contains("getRenderView")
            }
            if (getVideoViewMethod != null) {
                return getVideoViewMethod.invoke(player) as? android.view.View
            }
        } catch (e: Throwable) {
            Logger.error(e) { "VideoScreenshotPatch: failed to get video view" }
        }
        return null
    }

    /**
     * Save bitmap to storage and return the URI.
     */
    private fun saveBitmap(context: Context, bitmap: Bitmap): Uri? {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val filename = "BiliRoamingX_$timestamp.png"

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Android 10+ use MediaStore
            val contentValues = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, filename)
                put(MediaStore.Images.Media.MIME_TYPE, "image/png")
                put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/BiliRoamingX")
            }
            val resolver = context.contentResolver
            val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            if (uri != null) {
                resolver.openOutputStream(uri)?.use { outputStream ->
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                }
                uri
            } else null
        } else {
            // Android 9 and below use direct file access
            val directory = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "BiliRoamingX")
            if (!directory.exists()) directory.mkdirs()
            val file = File(directory, filename)
            FileOutputStream(file).use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            }
            Uri.fromFile(file)
        }
    }

    /**
     * Share the screenshot.
     */
    private fun shareScreenshot(context: Context, uri: Uri) {
        try {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "image/png"
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            context.startActivity(Intent.createChooser(intent, "分享截图"))
        } catch (e: Throwable) {
            Logger.error(e) { "VideoScreenshotPatch: failed to share screenshot" }
            Toasts.showShort("分享失败")
        }
    }
}
