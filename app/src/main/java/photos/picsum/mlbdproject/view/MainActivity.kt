package photos.picsum.mlbdproject.view


import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import kotlinx.coroutines.flow.collectLatest
import photos.picsum.mlbdproject.R
import photos.picsum.mlbdproject.databinding.ActivityMainBinding
import photos.picsum.mlbdproject.view_model.SharedViewModel
import com.google.android.material.snackbar.Snackbar
import photos.picsum.mlbdproject.utils.*
import photos.picsum.mlbdproject.utils.Constants.MY_PERMISSIONS_REQUEST_STORAGE


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


                if (CheckInternet.hasNetwork()) {
                    if (imageUrl.isNotEmpty()) {

                        if (CheckPermission.verifyPermissions(this@MainActivity)) {
                            DownloadFile.downloadImage(
                                activity = this@MainActivity,
                                imageUrl = imageUrl
                            )
                        }


                    }
                } else {
                    showSnackBar(getString(R.string.no_internet_connection))

                }


            }

            shareBtn.setOnClickListener {
                ShareImageLink.share(activity = this@MainActivity, link = imageUrl)
            }

        }

        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.pictureListFragment -> {

                    handleViewVisibility(View.GONE)

                }
                R.id.fullImageViewFragment -> {
                    handleViewVisibility(View.VISIBLE)
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

                        showSnackBar(it.msg)

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

    private fun showSnackBar(msg: String) {
        Snackbar.make(
            binding.root,
            msg, Snackbar.LENGTH_LONG
        ).show()
    }

    private fun handleViewVisibility(visibility: Int) {
        binding.apply {
            downloadImageBtn.visibility = visibility
            shareBtn.visibility = visibility
        }
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == MY_PERMISSIONS_REQUEST_STORAGE) {

            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                DownloadFile.downloadImage(
                    activity = this@MainActivity,
                    imageUrl = imageUrl
                )
            } else {

                showSnackBar(getString(R.string.permission_denied))
            }
        }
    }

}
