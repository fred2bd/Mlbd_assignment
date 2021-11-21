package photos.picsum.mlbdproject.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {


    private val _data = MutableSharedFlow<CommunicationState>()
    val data: SharedFlow<CommunicationState> get() = _data


    sealed class CommunicationState {
        data class Loading(val show: Boolean) : CommunicationState()
        data class Msg(val msg: String) : CommunicationState()
        data class DownloadImageUrl(val url: String) : CommunicationState()
        data class ToolbarTitle(val title: String) : CommunicationState()
    }

    fun showLoading(show: Boolean) {

        viewModelScope.launch (Dispatchers.Main){
            _data.emit(CommunicationState.Loading(show))

        }


    }

    fun showMsg(msg: String) {

        viewModelScope.launch (Dispatchers.Main){
            _data.emit(CommunicationState.Msg(msg))

        }
    }

    fun getTitle(title: String) {

        viewModelScope.launch (Dispatchers.Main){
            _data.emit(CommunicationState.ToolbarTitle(title))

        }
    }

    fun getImageUrl(url:String){
        viewModelScope.launch (Dispatchers.Main){
            _data.emit(CommunicationState.DownloadImageUrl(url))

        }
    }



}