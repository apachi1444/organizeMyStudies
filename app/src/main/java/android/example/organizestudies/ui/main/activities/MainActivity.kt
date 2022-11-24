package android.example.organizestudies.ui.main.activities

import android.app.Dialog
import android.content.Intent
import android.example.organizestudies.R
import android.example.organizestudies.databinding.ActivityMainBinding
import android.example.organizestudies.databinding.CustomPopupAddFileBinding
import android.example.organizestudies.utils.Const
import android.example.organizestudies.utils.Utils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private lateinit var bindingCustomPopupAddFileBinding: CustomPopupAddFileBinding

    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var mainActivityViewModel: MainActivityViewModel


    private val listModules = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        // for the view model
        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        // for the custom pop up model of adding a file to a specific module !
        val inflater = LayoutInflater.from(applicationContext)
        val view = inflater.inflate(R.layout.custom_popup_add_file, null)
        bindingCustomPopupAddFileBinding = CustomPopupAddFileBinding.bind(view)

        // this part for the controller
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
        val navController = navHostFragment.findNavController()


        mainActivityViewModel.allModules.observe(this) { it ->
            it.forEach { it ->
                it.modules.forEach {
                    listModules.add(it.moduleName)
                }
            }
        }
        configurationSpinnerModules()

        bottomNavigationLogic()

        NavigationUI.setupActionBarWithNavController(this, navController)
//        val my_shared_pref = getSharedPreferences("mine", Context.MODE_PRIVATE)
//        println(my_shared_pref)

    }

    private fun bottomNavigationLogic() {
        bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> Utils.showToast(applicationContext, "HOME")
                R.id.add -> {
                    showDialogAddFile()
                }
                R.id.settings -> Utils.showToast(applicationContext, "HERE")
            }
            true
        }

    }

    private fun choosePdfFromDevice() {
        bindingCustomPopupAddFileBinding.btnOk.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "application/*"
            startActivityForResult(intent, Const.CHOOSE_PDF_FROM_DEVICE)
        }

    }

    private fun showDialogAddFile() {
        val dialog = Dialog(this)
        bindingCustomPopupAddFileBinding =
            CustomPopupAddFileBinding.inflate(LayoutInflater.from(applicationContext))
        dialog.setContentView(bindingCustomPopupAddFileBinding.root)
        configurationSpinnerModules()
        dialog.show()
        choosePdfFromDevice()
        closeDialog(dialog)
    }

//    private fun getModulesNames(): List<String> {
//        val list = mainActivityViewModel.getModulesUser(this.lifecycleScope, applicationContext)
//        val finalList = ArrayList<String>()
//        list.forEach { it ->
//            it.modules.forEach {
//                finalList.add(it.moduleName)
//            }
//        }
//        return finalList
//    }

    private fun configurationSpinnerModules() {
        val spinner = bindingCustomPopupAddFileBinding.listModulesSpinner
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_item,
            listModules as List<CharSequence>
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }

    private fun closeDialog(dialog: Dialog) {
        val buttonClose = bindingCustomPopupAddFileBinding.btnClose
        buttonClose.setOnClickListener {
            dialog.dismiss()
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}