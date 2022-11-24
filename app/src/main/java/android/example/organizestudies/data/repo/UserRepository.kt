package android.example.organizestudies.data.repo

import android.app.Application
import android.example.organizestudies.data.dao.UserDao
import android.example.organizestudies.data.db.UserDb
import android.example.organizestudies.data.entities.User
import android.example.organizestudies.data.entities.relations.UserModuleCrossRef
import android.example.organizestudies.data.entities.relations.UserWithModules
import androidx.lifecycle.LiveData

// abstract access to multiple data sources
data class UserRepository(private val application: Application) {

    private var userDao: UserDao = UserDb.getInstance(application).userDbDao()
    private var readAllData: LiveData<List<User>> = userDao.getAllUsers()

    fun addUser(user: User) {
        userDao.insert(user)
    }

    fun getAllUsers(): LiveData<List<User>> {
        return readAllData
    }

    fun getModulesUser(userName: String): LiveData<List<UserWithModules>> {
        return userDao.getUsersWithModules(userName)
    }

    fun getUserByUsernameAndPassword(username: String, password: String): User {
        return userDao.getUserByUsernameAndPassword(username, password)
    }

    fun getUserByUsername(username: String): User {
        return userDao.getUserByUsername(username)
    }

    fun addUserModuleCrossRefModel(userModuleCrossRef: UserModuleCrossRef) {
        userDao.insert(userModuleCrossRef)
    }

}