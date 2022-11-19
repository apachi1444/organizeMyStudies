package android.example.organizestudies.viewmodels

import android.app.Application
import android.example.organizestudies.data.dao.UserDb
import android.example.organizestudies.data.entities.User
import android.example.organizestudies.data.repo.UserRepository
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(application: Application) : AndroidViewModel(application) {
//    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDb.getInstance(application).userDbDao()
        repository = UserRepository(userDao)
//        readAllData = repository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    suspend fun getAllUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            repository.getAllUsers()
        }

    }

}