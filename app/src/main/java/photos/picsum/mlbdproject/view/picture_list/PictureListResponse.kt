package photos.picsum.mlbdproject.view.picture_list


import com.google.gson.annotations.SerializedName

class PictureListResponse : ArrayList<PictureListResponse.PictureListResponseItem>(){
    data class PictureListResponseItem(
        @SerializedName("author")
        val author: String, // Anton Sulsky
        @SerializedName("download_url")
        val downloadUrl: String, // https://picsum.photos/id/116/3504/2336
        @SerializedName("height")
        val height: Int, // 2336
        @SerializedName("id")
        val id: String, // 116
        @SerializedName("url")
        val url: String, // https://unsplash.com/photos/YcfCXxo7rpc
        @SerializedName("width")
        val width: Int // 3504
    )
}