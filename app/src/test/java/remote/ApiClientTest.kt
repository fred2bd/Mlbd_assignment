package remote

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*
import org.junit.Test
import photos.picsum.mlbdproject.BuildConfig
import photos.picsum.mlbdproject.view.picture_list.PictureListResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class ApiClientTest {

    interface TestApiService {

        @GET("list?")
        fun getPictureList(
            @Query("page") page: Int,
            @Query("limit") limit: Int,
        ): Call<PictureListResponse>

    }


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

const val dummyResponse = """
 [
    {
        "id": "0",
        "author": "Alejandro Escamilla",
        "width": 5616,
        "height": 3744,
        "url": "https://unsplash.com/photos/yC-Yzbqy7PY",
        "download_url": "https://picsum.photos/id/0/5616/3744"
    },
    {
        "id": "1",
        "author": "Alejandro Escamilla",
        "width": 5616,
        "height": 3744,
        "url": "https://unsplash.com/photos/LNRyGwIJr5c",
        "download_url": "https://picsum.photos/id/1/5616/3744"
    },
    {
        "id": "10",
        "author": "Paul Jarvis",
        "width": 2500,
        "height": 1667,
        "url": "https://unsplash.com/photos/6J--NXulQCs",
        "download_url": "https://picsum.photos/id/10/2500/1667"
    },
    {
        "id": "100",
        "author": "Tina Rataj",
        "width": 2500,
        "height": 1656,
        "url": "https://unsplash.com/photos/pwaaqfoMibI",
        "download_url": "https://picsum.photos/id/100/2500/1656"
    },
    {
        "id": "1000",
        "author": "Lukas Budimaier",
        "width": 5626,
        "height": 3635,
        "url": "https://unsplash.com/photos/6cY-FvMlmkQ",
        "download_url": "https://picsum.photos/id/1000/5626/3635"
    }
]
"""
