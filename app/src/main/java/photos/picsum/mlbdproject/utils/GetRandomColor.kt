package photos.picsum.mlbdproject.utils

import android.content.Context
import photos.picsum.mlbdproject.view.MainApplication
import java.util.*

object GetRandomColor {


    fun color(): Int {
        val androidColors: IntArray =
            MainApplication.appContext.resources.getIntArray(photos.picsum.mlbdproject.R.array.android_colors)
        return androidColors[Random().nextInt(androidColors.size)]
    }
}