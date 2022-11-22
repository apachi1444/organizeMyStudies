package android.example.organizestudies.ui.main.fragments.homeFragment

import android.app.Application
import android.example.organizestudies.data.entities.User
import android.example.organizestudies.data.repo.UserRepository
import android.example.organizestudies.viewmodels.UserViewModel
import androidx.lifecycle.ViewModel

class HomeViewModel(private val application: Application) : ViewModel() {
    private val userRepository: UserRepository = UserRepository(application)
    private val userViewModel: UserViewModel = UserViewModel(application)

    fun getAllModules(user: User) {

    }

}