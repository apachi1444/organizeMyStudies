package android.example.organizestudies.data.repo

import android.example.organizestudies.data.dao.UserDbDao
import android.example.organizestudies.data.entities.User
import androidx.lifecycle.LiveData

// abstract access to multiple data sources
class UserRepository (private val userDbDao : UserDbDao){

    val readAllData: LiveData<List<User>> = userDbDao.getAllUsers()

    suspend fun addUser(user:User){
        userDbDao.insert(user)
    }
}