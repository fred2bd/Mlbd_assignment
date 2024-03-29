package photos.picsum.mlbdproject.view.picture_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import photos.picsum.mlbdproject.R
import photos.picsum.mlbdproject.databinding.FragmentPictureListBinding
import photos.picsum.mlbdproject.view.BaseFragment
import photos.picsum.mlbdproject.view_model.SharedViewModel

class PictureListFragment :
    BaseFragment<FragmentPictureListBinding>(FragmentPictureListBinding::inflate),
    PictureListAdapter.ClickCallBack {
    private val viewModel: PictureListViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val listAdapter by lazy { PictureListAdapter( this) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.getTitle(getString(R.string.gallery))


        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.callApi().collectLatest { pagingData ->
                binding.apply {
                    imageList.visibility=View.VISIBLE
                    imageList.adapter = listAdapter
                    imageList.hasFixedSize()

                }
                listAdapter.submitData(lifecycle = lifecycle, pagingData = pagingData)

            }

        }

    }

    override fun onItemClick(url: String) {

        val b = Bundle()
        b.putString(getString(R.string.image_url), url)

        findNavController().navigate(R.id.action_pictureListFragment_to_fullImageViewFragment, b)

    }

}