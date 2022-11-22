package android.example.organizestudies.viewmodels

import android.app.Application
import android.example.organizestudies.data.entities.User
import android.example.organizestudies.data.repo.UserRepository
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository
    private var readAllData: LiveData<List<User>>

    init {
        repository = UserRepository(application)
        readAllData = repository.getAllUsers()
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun delete(user: User) {

    }

    fun getAllUsers(): LiveData<List<User>> {
        return readAllData
    }


    suspend fun getUserByUsernameAndPassword(username: String, password: String): User {
        return withContext(Dispatchers.IO) {
            repository.getUserByUsernameAndPassword(username, password)
        }
    }

    suspend fun getUserByUsername(username: String): User {
        return withContext(Dispatchers.IO) {
            repository.getUserByUsername(username)
        }

    }

}