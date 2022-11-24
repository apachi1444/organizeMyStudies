package android.example.organizestudies.ui.main.fragments.homeFragment

import android.app.Application
import android.example.organizestudies.data.entities.relations.UserWithModules
import android.example.organizestudies.data.repo.UserRepository
import android.example.organizestudies.utils.Utils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository = UserRepository(application)
    val allModules: LiveData<List<UserWithModules>> =
        userRepository.getModulesUser(Utils.readingFromSharedPreferences(application, "username")!!)
}