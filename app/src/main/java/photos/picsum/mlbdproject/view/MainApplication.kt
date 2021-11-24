package photos.picsum.mlbdproject.view

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class MainApplication:Application() {
    override fun onCreate() {
        super.onCreate()
       // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        appContext = applicationContext

    }
    companion object {

        lateinit  var appContext: Context

    }
}