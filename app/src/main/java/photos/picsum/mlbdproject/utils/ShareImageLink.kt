package photos.picsum.mlbdproject.utils

import android.content.Intent
import photos.picsum.mlbdproject.view.MainApplication

object ShareImageLink {
    fun share(link:String){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, link)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        MainApplication.appContext.startActivity(shareIntent)

    }
}