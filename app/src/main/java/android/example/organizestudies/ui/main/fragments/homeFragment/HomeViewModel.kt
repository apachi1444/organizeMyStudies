package android.example.organizestudies.ui.main.fragments.homeFragment

import android.app.Application
import android.example.organizestudies.data.entities.relations.UserWithModules
import android.example.organizestudies.data.repo.UserRepository
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository = UserRepository(application)

    suspend fun getAllModules(userName: String): List<UserWithModules> {
        return withContext(Dispatchers.IO) {
            userRepository.getModulesUser(userName)
        }
    }

}