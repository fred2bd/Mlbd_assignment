package photos.picsum.mlbdproject.model.remote
import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {


    fun provideApiService(): ApiService {

        return provideClient().create(ApiService::class.java)

    }

    private fun provideClient(): Retrofit {

        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
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