package android.example.organizestudies.viewmodels

import android.app.Application
import android.example.organizestudies.data.entities.User
import android.example.organizestudies.data.entities.relations.UserModuleCrossRef
import android.example.organizestudies.data.entities.relations.UserModuleId
import android.example.organizestudies.data.entities.relations.UserWithModules
import android.example.organizestudies.data.repo.ModuleRepository
import android.example.organizestudies.data.repo.UserRepository
import android.example.organizestudies.utils.ModuleUtils
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository
    private val moduleRepository: ModuleRepository
    private var readAllData: LiveData<List<User>>

    init {
        moduleRepository = ModuleRepository(application)
        userRepository = UserRepository(application)
        readAllData = userRepository.getAllUsers()
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user)
        }
    }

    fun getModulesUser(userId: String): List<UserWithModules> {
        return userRepository.getModulesUser(userId)
    }

    fun addModules(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            val modules = ModuleUtils.getModules(user)
            modules.forEach {
                userRepository.addUserModuleCrossRefModel(
                    UserModuleCrossRef(
                        UserModuleId(user.userId, it.moduleId),
                        user.username,
                        it.moduleName,
                        false
                    )
                )
            }
            userRepository.getModulesUser(user.userId).forEach {
                Log.i("haha", it.toString())
            }
        }
    }

    fun addModulesToUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {

            val grade = user.grade
            val levelStudy = user.levelStudy

            val listModules = moduleRepository
                .getModulesByGradeAndLevelStudy(grade, levelStudy)

            listModules.forEach {

                val userModuleCrossRef =
                    UserModuleCrossRef(
                        UserModuleId(
                            user.userId,
                            it.moduleId
                        ),
                        user.username,
                        it.moduleName,
                        false
                    )

                userRepository.addUserModuleCrossRefModel(userModuleCrossRef)
            }
        }
    }


    fun getAllUsers(): LiveData<List<User>> {
        return readAllData
    }


    suspend fun getUserByUsernameAndPassword(username: String, password: String): User {
        return withContext(Dispatchers.IO) {
            userRepository.getUserByUsernameAndPassword(username, password)
        }
    }

    suspend fun getUserByUsername(username: String): User {
        return withContext(Dispatchers.IO) {
            userRepository.getUserByUsername(username)
        }

    }

}