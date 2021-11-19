package photos.picsum.mlbdproject.model.remote


import photos.picsum.mlbdproject.view.picture_list.PictureListResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("list?")
    suspend fun searchApi(
        @Query("page_number") page: Int,
        @Query("limit") limit: Int,
    ): Response<PictureListResponse>

}