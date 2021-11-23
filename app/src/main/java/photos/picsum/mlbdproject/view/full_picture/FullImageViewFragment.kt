package photos.picsum.mlbdproject.view.full_picture

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import photos.picsum.mlbdproject.R
import photos.picsum.mlbdproject.databinding.FragmentFullImageViewBinding
import photos.picsum.mlbdproject.utils.LoadImage
import photos.picsum.mlbdproject.view.BaseFragment
import photos.picsum.mlbdproject.view_model.SharedViewModel
import uk.co.senab.photoview.PhotoViewAttacher


class FullImageViewFragment :
    BaseFragment<FragmentFullImageViewBinding>(FragmentFullImageViewBinding::inflate) {
    private var imageUrl: String = ""
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var photoAttacher: PhotoViewAttacher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel.getTitle("")
        imageUrl = arguments?.getString(getString(R.string.image_url))!!
        sharedViewModel.getImageUrl(imageUrl)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoAttacher = PhotoViewAttacher(binding.fillImageView)  // Add zoom in/out functionality to a imageview
        photoAttacher.update()


        binding.apply {
            LoadImage.load(
                imageView = binding.fillImageView,
                link = imageUrl, loader = imageLoader
            ) //Load image using Glide


        }


    }


}