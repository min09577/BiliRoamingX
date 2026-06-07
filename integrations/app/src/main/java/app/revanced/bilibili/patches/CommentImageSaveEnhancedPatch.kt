package app.revanced.bilibili.patches

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.Toasts
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

@Keep
object CommentImageSaveEnhancedPatch {

    @JvmStatic
    fun saveImage(context: Context?, bitmap: Bitmap?, imageUrl: String?): Boolean {
        if (!Settings.CommentImageSaveEnhanced.get()) return false
        if (context == null || bitmap == null) return false

        try {
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val filename = "BiliRoamingX_comment_$timestamp.png"

            val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
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
                val directory = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "BiliRoamingX")
                if (!directory.exists()) directory.mkdirs()
                val file = File(directory, filename)
                FileOutputStream(file).use { outputStream ->
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                }
                Uri.fromFile(file)
            }

            if (uri != null) {
                Toasts.showShort("图片已保存到相册")
                Logger.debug { "CommentImageSaveEnhanced: saved to $uri" }
                return true
            }
        } catch (e: Throwable) {
            Logger.error(e) { "CommentImageSaveEnhanced: failed to save" }
            Toasts.showShort("保存失败")
        }
        return false
    }

    @JvmStatic
    fun isEnabled(): Boolean {
        return Settings.CommentImageSaveEnhanced.get()
    }
}
