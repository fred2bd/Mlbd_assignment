package photos.picsum.mlbdproject.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

object ShareImageLink {
    fun share(context: Context,link:String){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, link)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)

    }
}