package photos.picsum.mlbdproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import kotlinx.coroutines.flow.collectLatest
import photos.picsum.mlbdproject.R
import photos.picsum.mlbdproject.databinding.ActivityMainBinding
import photos.picsum.mlbdproject.utils.DownloadFile
import photos.picsum.mlbdproject.view_model.SharedViewModel

class MainActivity : AppCompatActivity() {
    private var imageUrl: String = ""
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val sharedViewModel: SharedViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        binding.apply {

            backBtn.setOnClickListener {
                onBackPressed()


            }
            downloadImageBtn.setOnClickListener {

                if (imageUrl.isNotEmpty()) {
                    DownloadFile.downloadImage(activity = this@MainActivity, imageUrl = imageUrl)
                }


            }


        }

        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.pictureListFragment -> {

                    binding.downloadImageBtn.visibility = View.GONE

                }
                R.id.fullImageViewFragment -> {
                    binding.downloadImageBtn.visibility = View.VISIBLE

                }
                else -> Unit
            }


        }

        lifecycleScope.launchWhenStarted {
            sharedViewModel.data.collectLatest {

                when (it) {
                    is SharedViewModel.CommunicationState.Loading -> {

                        if (it.show) {

                            binding.progressBar.visibility = View.VISIBLE

                        } else {
                            binding.progressBar.visibility = View.GONE

                        }

                    }
                    is SharedViewModel.CommunicationState.Msg -> {
                        Toast.makeText(this@MainActivity, it.msg, Toast.LENGTH_LONG).show()
                    }
                    is SharedViewModel.CommunicationState.DownloadImageUrl -> {
                        imageUrl = it.url
                    }

                    is SharedViewModel.CommunicationState.ToolbarTitle -> {
                        binding.toolbarTitleTextView.text = it.title
                    }
                }

            }
        }


    }

}
