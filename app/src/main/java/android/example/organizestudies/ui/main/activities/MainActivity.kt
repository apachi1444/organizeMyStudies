package android.example.organizestudies.ui.main.activities

import android.app.Dialog
import android.example.organizestudies.R
import android.example.organizestudies.databinding.ActivityMainBinding
import android.example.organizestudies.utils.Utils
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
        val navController = navHostFragment.findNavController()

        bottomNavigationLogic()

        NavigationUI.setupActionBarWithNavController(this, navController)
//        val my_shared_pref = getSharedPreferences("mine", Context.MODE_PRIVATE)
//
//        println(my_shared_pref)


    }

    private fun bottomNavigationLogic() {
        bottomNavigationView = binding.bottomNavigation

        // when we click on the plus button
        // a pop up will appear showing all modules available
        // and then we should select the module to insert into it
        // the file selected !

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> Utils.showToast(applicationContext, "HOME")
                R.id.add -> {
                    Utils.showToast(applicationContext, "HAHA")
                    showDialogAddFile()
                }
                R.id.settings -> Utils.showToast(applicationContext, "HERE")
            }
            true
        }

    }

    private fun showDialogAddFile() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_popup_add_file)
        dialog.show()
        closeDialog(dialog)
    }

    private fun closeDialog(dialog: Dialog) {
        val buttonClose = dialog.findViewById<ImageView>(R.id.btn_close)
        buttonClose.setOnClickListener {
            dialog.dismiss()
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val menuInflater =
//        inflater.inflate(R.menu.menu_main, menu)
//        return true
//    }


}