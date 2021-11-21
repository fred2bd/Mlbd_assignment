package photos.picsum.mlbdproject.view.picture_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import photos.picsum.mlbdproject.databinding.FragmentPictureListBinding
import photos.picsum.mlbdproject.view.BaseFragment
import photos.picsum.mlbdproject.view_model.SharedViewModel

class PictureListFragment : BaseFragment<FragmentPictureListBinding>(FragmentPictureListBinding::inflate) {

    private val viewModel:PictureListViewModel by viewModels()
    private val sharedViewModel:SharedViewModel by activityViewModels()
    private val listAdapter by lazy { PictureListAdapter(requireContext()) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.callApi().collectLatest   { pagingData->
                binding.imageList.adapter=listAdapter
                binding.imageList.hasFixedSize()
                listAdapter.submitData(lifecycle = lifecycle, pagingData = pagingData)

            }

        }

    }

}