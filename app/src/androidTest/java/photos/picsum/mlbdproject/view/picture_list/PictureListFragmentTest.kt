package photos.picsum.mlbdproject.view.picture_list

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import org.junit.Test
import photos.picsum.mlbdproject.BuildConfig
import photos.picsum.mlbdproject.R
import photos.picsum.mlbdproject.view.MainActivity

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import photos.picsum.mlbdproject.DummyData.dummyResponse
import photos.picsum.mlbdproject.IntentsTestRule
import photos.picsum.mlbdproject.TestApiService

class PictureListFragmentTest {

    @Test
    fun checkIfRecyclerviewIsShowingIfApiLoadsDataSuccessfully() {
        val mockServer = MockWebServer()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
        mockServer.enqueue(MockResponse().setBody(dummyResponse))
        val service = retrofit.create(TestApiService::class.java)
        val call = service.getPictureList(1, 5)
        mockServer.shutdown()


        val firstActivity: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)
        firstActivity.launchActivity(Intent())

        if (call.execute().isSuccessful){
            onView(withId(R.id.imageList)).check(matches(ViewMatchers.isDisplayed()))

        }


    }




}

