package remote

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*
import org.junit.Test
import photos.picsum.mlbdproject.BuildConfig
import DummyData.dummyResponse
import TestApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClientTest {



    @Test
    fun `test getPictureList api response is not empty with dummy data `() {
        val mockServer = MockWebServer()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
        mockServer.enqueue(MockResponse().setBody(dummyResponse))
        val service = retrofit.create(TestApiService::class.java)
        val call = service.getPictureList(1, 5)
        call.execute().body()?.let { assertTrue(it.isNotEmpty()) }
        mockServer.shutdown()

    }


}

