package photos.picsum.mlbdproject.view.full_picture

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import photos.picsum.mlbdproject.R
import photos.picsum.mlbdproject.databinding.FragmentFullImageViewBinding
import photos.picsum.mlbdproject.view.BaseFragment

class FullImageViewFragment :
    BaseFragment<FragmentFullImageViewBinding>(FragmentFullImageViewBinding::inflate) {

    private var imageUrl:String=""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imageUrl= arguments?.getString("imageUrl")!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}