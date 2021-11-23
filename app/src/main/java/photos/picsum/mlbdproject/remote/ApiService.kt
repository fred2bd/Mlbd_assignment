package photos.picsum.mlbdproject.remote


import photos.picsum.mlbdproject.view.picture_list.PictureListResponse
import retrofit2.http.*

interface ApiService {

    @GET("list?")
    suspend fun searchApi(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): PictureListResponse

}