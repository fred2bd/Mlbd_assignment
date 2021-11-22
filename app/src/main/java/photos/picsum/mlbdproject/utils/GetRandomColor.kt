package photos.picsum.mlbdproject.utils

import android.content.Context
import java.util.*

object GetRandomColor {


    fun color(context: Context): Int {
        val androidColors: IntArray =
            context.resources.getIntArray(photos.picsum.mlbdproject.R.array.android_colors)
        return androidColors[Random().nextInt(androidColors.size)]
    }
}