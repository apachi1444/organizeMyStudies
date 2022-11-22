package android.example.organizestudies.ui.main.fragments.homeFragment

import android.app.Application
import android.example.organizestudies.data.entities.User
import android.example.organizestudies.data.entities.relations.UserWithModules
import android.example.organizestudies.data.repo.UserRepository
import android.example.organizestudies.viewmodels.UserViewModel
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository = UserRepository(application)
    private val userViewModel: UserViewModel = UserViewModel(application)

    fun getAllModules(userId : String): List<UserWithModules> {
        return userViewModel.getModulesUser(userId)
    }

}