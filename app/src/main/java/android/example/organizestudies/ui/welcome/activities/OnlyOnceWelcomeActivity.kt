package android.example.organizestudies.ui.welcome.activities

import android.example.organizestudies.R
import android.example.organizestudies.databinding.ActivityOnlyOnceWelcomeBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

class OnlyOnceWelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnlyOnceWelcomeBinding
    private lateinit var navController: NavController
    private lateinit var welcomeViewModel: WelcomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_only_once_welcome)

        welcomeViewModel = ViewModelProvider(this)[WelcomeViewModel::class.java]

//        addingModulesToDb()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()

//        setupActionBarWithNavController(navController)
//
//        NavigationUI.setupActionBarWithNavController(this, navController)
    }

//    private fun addingModulesToDb() {
//        welcomeViewModel.addModules()
//    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}