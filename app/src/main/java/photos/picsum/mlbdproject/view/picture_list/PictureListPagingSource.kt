package photos.picsum.mlbdproject.view.picture_list

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import remote.ApiClient
import retrofit2.HttpException
import java.io.IOException

class PictureListPagingSource: PagingSource<Int, PictureListResponse.PictureListResponseItem>() {
    override fun getRefreshKey(state: PagingState<Int, PictureListResponse.PictureListResponseItem>): Int? {
        return state.anchorPosition

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PictureListResponse.PictureListResponseItem> {

        val position = params.key ?: 1

        return try {

            val response = ApiClient.provideApiService().getPictureList(page = position, limit =params.loadSize)

            val nextKey = if (response.isEmpty()) null else    position + (params.loadSize / DEFAULT_BUFFER_SIZE)


            Log.e("nextKey",nextKey.toString())
            Log.e("position",position.toString())

            LoadResult.Page(
                data = response,
                prevKey = if (position == 1) null else position - 1,
                nextKey = nextKey
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }

    }
}