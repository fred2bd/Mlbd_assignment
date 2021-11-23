package photos.picsum.mlbdproject.view.full_picture

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.activityViewModels
import photos.picsum.mlbdproject.R
import photos.picsum.mlbdproject.databinding.FragmentFullImageViewBinding
import photos.picsum.mlbdproject.utils.LoadImage
import photos.picsum.mlbdproject.view.BaseFragment
import photos.picsum.mlbdproject.view_model.SharedViewModel


class FullImageViewFragment :
    BaseFragment<FragmentFullImageViewBinding>(FragmentFullImageViewBinding::inflate) {
    private var imageUrl: String = ""
    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel.getTitle("")
        imageUrl = arguments?.getString(getString(R.string.image_url))!!
        sharedViewModel.getImageUrl(imageUrl)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            LoadImage.load(
                imageView = binding.fillImageView,
                link = imageUrl, loader = imageLoader
            )
        }




    }


}