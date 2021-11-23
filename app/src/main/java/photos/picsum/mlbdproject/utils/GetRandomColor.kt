package photos.picsum.mlbdproject.utils

import photos.picsum.mlbdproject.view.MainApplication
import java.util.*

object GetRandomColor {
    /*
     * This function is used to generate random color from android_colors array
     */

    fun color(): Int {
        val androidColors: IntArray =
            MainApplication.appContext.resources.getIntArray(photos.picsum.mlbdproject.R.array.android_colors)
        return androidColors[Random().nextInt(androidColors.size)]
    }
}