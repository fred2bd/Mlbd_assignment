package photos.picsum.mlbdproject.view.picture_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import photos.picsum.mlbdproject.databinding.ImageListLayoutBinding
import photos.picsum.mlbdproject.utils.LoadImage
import java.util.*


class PictureListAdapter(private val context: Context, private val clickCallBack: ClickCallBack) :
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

            link.text = getItem(position)!!.author
            LoadImage.load(
                context,
                link = getItem(position)!!.downloadUrl,
                imageView = linkImageView,imageLoader
            )

            root.setOnClickListener {

                getItem(position)?.let { it1 -> clickCallBack.onItemClick(it1.downloadUrl) }

            }
            val androidColors: IntArray = context.getResources().getIntArray(photos.picsum.mlbdproject.R.array.androidcolors)
            val randColor=androidColors[Random().nextInt(androidColors.size)]
            link.setBackgroundColor(randColor)

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