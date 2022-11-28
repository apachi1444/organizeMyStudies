package android.example.organizestudies.ui.main.activities

import android.app.Dialog
import android.content.Intent
import android.example.organizestudies.R
import android.example.organizestudies.data.entities.File
import android.example.organizestudies.databinding.ActivityMainBinding
import android.example.organizestudies.databinding.CustomPopupAddFileBinding
import android.example.organizestudies.utils.DateUtils
import android.example.organizestudies.utils.StringsUtils
import android.example.organizestudies.utils.Utils
import android.example.organizestudies.utils.consts.ConstKeys
import android.example.organizestudies.utils.consts.ObjectStorage
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.result.contract.ActivityResultContracts
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
    private lateinit var spinner: Spinner
    private lateinit var dialog: Dialog
    private var currentSpinnerItem: String = ""
    private val listModules = ArrayList<String>()
    private var uriFile: String = ""

    // Receiver
    private val getResult =
        registerForActivityResult(ActivityResultContracts.OpenDocument())
        { result: Uri? ->
            val data: String? = result?.encodedPath
            if (data != null) {
                val returnCursor = contentResolver.query(result, null, null, null, null)!!
                val nameIndex: Int = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                returnCursor.moveToFirst()
                val name: String = returnCursor.getString(nameIndex)
                returnCursor.close()
                bindingCustomPopupAddFileBinding.fileName.text =
                    name
                uriFile = name
                closeDialog(dialog)
            } else {
                bindingCustomPopupAddFileBinding.fileName.text =
                    StringsUtils.MESSAGE_ERROR_WHEN_FILE_UPLOADED
            }
        }

    private fun moduleUserIdCombined() = mainActivityViewModel.getUserModuleIdCombined(
        Utils.readingFromSharedPreferences(
            applicationContext,
            ConstKeys.USERNAME
        )!!,
        currentSpinnerItem
    )


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
        spinner = bindingCustomPopupAddFileBinding.listModulesSpinner

        dialog = Dialog(this)
        dialog.setContentView(bindingCustomPopupAddFileBinding.root)

        // this part for the controller
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

        val navController = navHostFragment.findNavController()

        mainActivityViewModel.allModules.observe(this) { it ->
            it.forEach { ite ->
                ite.modules.forEach {
                    listModules.add(it.moduleName)
                }
            }
        }
        configurationSpinnerModules()
//        bottomNavigationLogic()
        fabLogic()
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    private fun fabLogic() {
        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            showDialogAddFile()
        }
    }

//    private fun bottomNavigationLogic() {
//        bottomNavigationView = binding.bottomNavigation
//        bottomNavigationView.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.home -> {
////                    supportFragmentManager.commit {
////
////                        setReorderingAllowed(true).replace<HomeFragment>(R.id.nav_host_fragment_container)
////                    }
//                }
//                R.id.add -> {
//                    showDialogAddFile()
//                }
//                R.id.settings -> Utils.showToast(applicationContext, "HERE")
//            }
//            true
//        }
//    }
//
//    private fun choosePdfFromDevice(): ActivityResultLauncher<Intent> {
////        bindingCustomPopupAddFileBinding.btnOk.setOnClickListener {
////            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
////            intent.addCategory(Intent.CATEGORY_OPENABLE)
////            intent.type = "application/*"
////            startActivityForResult(intent, Const.CHOOSE_PDF_FROM_DEVICE)
////        }
//
//
//    }

    private fun showDialogAddFile() {

        configurationSpinnerModules()

        bindingCustomPopupAddFileBinding.fileName.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/pdf"
            getResult.launch(arrayOf("application/pdf"))
        }
        dialog.show()
        closeDialog(dialog)
    }

    private fun addFileToDb() {
        val file = File(
            uriFile,
            ObjectStorage.PDF_EXTENSION,
            false,
            DateUtils.getDateNow(),
            DateUtils.getDateNow(),
            moduleUserIdCombined(),
            mainActivityViewModel.getModuleImage(currentSpinnerItem),
            currentSpinnerItem,
            Utils.readingFromSharedPreferences(applicationContext, ConstKeys.USERNAME)!!,
        )
        mainActivityViewModel.addFile(file)
    }

    private fun configurationSpinnerModules() {
        listModules.forEach {
            Log.i("haha", it)
        }

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

        bindingCustomPopupAddFileBinding.btnOk.setOnClickListener {
            addFileToDb()
            dialog.dismiss()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        currentSpinnerItem = parent?.selectedItem.toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}