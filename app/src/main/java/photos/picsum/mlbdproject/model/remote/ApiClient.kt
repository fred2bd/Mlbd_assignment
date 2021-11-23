package photos.picsum.mlbdproject.model.remote

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

import java.util.concurrent.TimeUnit
import photos.picsum.mlbdproject.model.remote.CacheInterceptor.networkInterceptor
import photos.picsum.mlbdproject.model.remote.CacheInterceptor.offlineInterceptor
import photos.picsum.mlbdproject.view.MainApplication

object ApiClient {

    private const val cacheSize = (5 * 1024 * 1024).toLong()
    private val cache = Cache(File(MainApplication.appContext.cacheDir, "http"), cacheSize)
    fun provideApiService(): ApiService {

        return provideClient().create(ApiService::class.java)

    }

    private fun provideClient(): Retrofit {


        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder()
            .cache(cache)
            .addNetworkInterceptor(networkInterceptor()) //when network is On or Off
            .addInterceptor(offlineInterceptor()) //when network is Off
            .connectTimeout(140, TimeUnit.SECONDS)
            .readTimeout(140, TimeUnit.SECONDS)
            .writeTimeout(140, TimeUnit.SECONDS)
            .build()


        return Retrofit.Builder()
            .baseUrl("https://picsum.photos/v2/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}
