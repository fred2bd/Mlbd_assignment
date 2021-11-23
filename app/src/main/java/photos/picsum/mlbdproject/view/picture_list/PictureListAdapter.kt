package photos.picsum.mlbdproject.view.picture_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import photos.picsum.mlbdproject.databinding.ImageListLayoutBinding
import photos.picsum.mlbdproject.utils.GetRandomColor
import photos.picsum.mlbdproject.utils.LoadImage



class PictureListAdapter(private val clickCallBack: ClickCallBack) :
    PagingDataAdapter<PictureListResponse.PictureListResponseItem, PictureListAdapter.ListViewHolder>(
        DiffClass()
    ) {

    class DiffClass : DiffUtil.ItemCallback<PictureListResponse.PictureListResponseItem>() {
        override fun areItemsTheSame(
            oldItem: PictureListResponse.PictureListResponseItem,
            newItem: PictureListResponse.PictureListResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PictureListResponse.PictureListResponseItem,
            newItem: PictureListResponse.PictureListResponseItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    inner class ListViewHolder(val binding: ImageListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: PictureListAdapter.ListViewHolder, position: Int) {



        holder.binding.apply {

            authorTextView.text = getItem(position)!!.author
            LoadImage.load(
                link = getItem(position)!!.downloadUrl,
                imageView = linkImageView,imageLoader
            ) //Load image using Glide

            root.setOnClickListener {

                getItem(position)?.let { it1 -> clickCallBack.onItemClick(it1.downloadUrl) } // Click listener callback to get downloadable link in fragment

            }

            authorTextView.setBackgroundColor(GetRandomColor.color()) // Set background by generating random color

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PictureListAdapter.ListViewHolder {
        val binding =
            ImageListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return ListViewHolder(binding)
    }


    interface ClickCallBack {
        fun onItemClick(url: String)

    }



}