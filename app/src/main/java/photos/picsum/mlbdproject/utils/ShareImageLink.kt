package photos.picsum.mlbdproject.utils

import android.app.Activity
import android.content.Intent

object ShareImageLink {

    /*
  * This function is used to share image link with via other apps
  */

    fun share(activity: Activity, link: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, link)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        activity.startActivity(shareIntent)

    }
}