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
import photos.picsum.mlbdproject.view.picture_list.PictureListViewModel
import photos.picsum.mlbdproject.view_model.SharedViewModel

class MainActivity : AppCompatActivity() {

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

        binding.backBtn.setOnClickListener {
            onBackPressed()


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

                }

            }
        }


    }

}
