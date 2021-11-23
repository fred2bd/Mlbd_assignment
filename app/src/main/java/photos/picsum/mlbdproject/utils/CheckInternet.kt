package photos.picsum.mlbdproject.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import photos.picsum.mlbdproject.view.MainApplication


object CheckInternet {

    /*
        * This function is used to check internet connection status
    */
    fun hasNetwork(): Boolean {
        var isConnected = false // Initial Value
        val connectivityManager =
            MainApplication.appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        isConnected = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return isConnected
    }
}