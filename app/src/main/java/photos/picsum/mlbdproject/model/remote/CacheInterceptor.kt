package photos.picsum.mlbdproject.model.remote

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import photos.picsum.mlbdproject.utils.CheckInternet
import photos.picsum.mlbdproject.view.MainApplication
import java.util.concurrent.TimeUnit

object CacheInterceptor {

     fun networkInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response: Response = chain.proceed(chain.request())
            val cacheControl: CacheControl = CacheControl.Builder()
                .maxAge(5, TimeUnit.SECONDS)
                .build()
            response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", cacheControl.toString())
                .build()
        }
    }


     fun offlineInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()

            // prevent caching when network is on. For that we use the "networkInterceptor"
            if (!CheckInternet.hasNetwork()) {
                val cacheControl: CacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()
                request = request.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
    }

}