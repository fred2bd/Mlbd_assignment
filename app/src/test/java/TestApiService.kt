import photos.picsum.mlbdproject.view.picture_list.PictureListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TestApiService {

    @GET("list?")
    fun getPictureList(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Call<PictureListResponse>

}
