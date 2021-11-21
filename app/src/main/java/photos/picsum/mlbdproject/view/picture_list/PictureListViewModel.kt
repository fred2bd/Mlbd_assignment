package photos.picsum.mlbdproject.view.picture_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class PictureListViewModel : ViewModel() {

    fun callApi(): Flow<PagingData<PictureListResponse.PictureListResponseItem>> {
        return Pager(config = PagingConfig(
            pageSize = DEFAULT_BUFFER_SIZE,
            enablePlaceholders = false
        ),
            pagingSourceFactory = { PictureListPagingSource() }
        ).flow.cachedIn(viewModelScope)

    }


}