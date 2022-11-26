package android.example.organizestudies.ui.main.fragments.profileFragment

import android.app.Application
import android.example.organizestudies.data.entities.User
import android.example.organizestudies.data.repo.FileRepository
import android.example.organizestudies.data.repo.UserModuleCrossRefRepository
import android.example.organizestudies.data.repo.UserRepository
import androidx.lifecycle.AndroidViewModel

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val fileRepository: FileRepository = FileRepository(application)
    private val userModuleCrossRefRepository: UserModuleCrossRefRepository =
        UserModuleCrossRefRepository(application)

    private val userRepository: UserRepository = UserRepository(application)

    fun countUserModules(username: String): String {
        return userModuleCrossRefRepository.countUserModules(username).toString()
    }

    fun countUserFiles(username: String): String {
        return fileRepository.countUserFiles(username).toString()
    }

    fun getUserByUsername(username: String): User {
        return userRepository.getUserByUsername(username)
    }

}