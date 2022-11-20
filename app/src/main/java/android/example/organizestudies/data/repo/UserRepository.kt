package android.example.organizestudies.data.repo

import android.example.organizestudies.data.dao.UserDbDao
import android.example.organizestudies.data.entities.User

// abstract access to multiple data sources
class UserRepository(private val userDbDao: UserDbDao) {

//    val readAllData: LiveData<List<User>> = userDbDao.getAllUsers()

    fun addUser(user: User) {
        userDbDao.insert(user)
    }

    fun getAllUsers(): List<User> {
        return userDbDao.getAllUsers()
    }

    fun getUserByUsernameAndPassword(username: String, password: String): User {
        return userDbDao.getUserByUsernameAndPassword(username, password)
    }

    fun getUserByUsername(username: String) : User {
        return userDbDao.getUserByUsername(username)
    }
}