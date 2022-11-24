package android.example.organizestudies.ui.main.fragments.homeFragment

import android.app.Application
import android.content.Context
import android.example.organizestudies.data.entities.relations.UserWithModules
import android.example.organizestudies.data.repo.UserRepository
import android.example.organizestudies.utils.Utils
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository = UserRepository(application)


//    fun getModulesUser(
//        lifecycleScope: CoroutineScope,
//        context: Context
//    ): List<UserWithModules> {
//
//        var listModules: List<UserWithModules> = ArrayList()
//
//        lifecycleScope.launch(Dispatchers.Main) {
//            listModules = userRepository.getModulesUser(
//                Utils.readingFromSharedPreferences(
//                    context,
//                    "username"
//                )!!
//            )
//        }
//        return listModules
//    }

}