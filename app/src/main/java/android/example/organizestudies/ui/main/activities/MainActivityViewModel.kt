package android.example.organizestudies.ui.main.activities

import android.app.Application
import android.example.organizestudies.data.entities.File
import android.example.organizestudies.data.entities.relations.UserWithModules
import android.example.organizestudies.data.repo.FileRepository
import android.example.organizestudies.data.repo.ModuleRepository
import android.example.organizestudies.data.repo.UserModuleCrossRefRepository
import android.example.organizestudies.data.repo.UserRepository
import android.example.organizestudies.utils.Utils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private var userRepository: UserRepository = UserRepository(application)
    private var fileRepository: FileRepository = FileRepository(application)
    private var moduleRepository: ModuleRepository = ModuleRepository(application)
    private var userModuleCrossRefRepository = UserModuleCrossRefRepository(application)

    val allModules: LiveData<List<UserWithModules>> =
        userRepository.getModulesUser(Utils.readingFromSharedPreferences(application, "username")!!)


    fun addFile(file: File) =
        viewModelScope.launch(Dispatchers.IO) {
            fileRepository.insertFile(file)
        }

    fun getUserModuleIdCombined(username: String, moduleName: String): String {
        return userModuleCrossRefRepository.getOneIdCombined(username, moduleName)
    }

    fun getModuleImage(moduleName: String): Int {
        return moduleRepository.getModuleImage(
            moduleName
        )
    }

    fun goToFragmentFromBottomBarNavigation(navController: NavController, action : Int){
        navController.navigate(action)
    }


    //    fun getModulesUser(
//        lifecycleScope: CoroutineScope,
//        context: Context
//    ): List<UserWithModules> {
//
////        var listModules: LiveData<List<UserWithModules>> = ArrayList()
////
////        lifecycleScope.launch(Dispatchers.Main) {
////            listModules = userRepository.getModulesUser(
////                Utils.readingFromSharedPreferences(
////                    context,
////                    "username"
////                )!!
////            )
////        }
////        return listModules
//        return null
//    }

}