package photos.picsum.mlbdproject.utils

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.webkit.CookieManager
import android.webkit.URLUtil
import android.widget.Toast
import photos.picsum.mlbdproject.R

object DownloadFile {
    /*
     * This function is used to download image file from url
     */
    fun downloadImage(activity: Activity, imageUrl: String) {


        val request = DownloadManager.Request(Uri.parse(imageUrl))
        val title = URLUtil.guessFileName(imageUrl, null, "image/jpeg")
        request.setTitle(title)
        request.setDescription(activity.getString(R.string.downloading))
        val cookie = CookieManager.getInstance().getCookie(imageUrl)
        request.addRequestHeader("cookie", cookie)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DCIM, title)
        val downloadManager = activity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
        Toast.makeText(activity, activity.getString(R.string.downloading), Toast.LENGTH_LONG).show()
    }

}