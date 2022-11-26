package android.example.organizestudies.ui.main.fragments.homeFragment

import android.app.Application
import android.example.organizestudies.data.entities.File
import android.example.organizestudies.data.entities.relations.UserWithModules
import android.example.organizestudies.data.repo.FileRepository
import android.example.organizestudies.data.repo.UserModuleCrossRefRepository
import android.example.organizestudies.data.repo.UserRepository
import android.example.organizestudies.utils.Utils
import android.example.organizestudies.utils.consts.ConstKeys
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository = UserRepository(application)
    private val fileRepository: FileRepository = FileRepository(application)
    private val userModuleCrossRefRepository = UserModuleCrossRefRepository(application)

    val allModules: LiveData<List<UserWithModules>> =
        userRepository.getModulesUser(
            Utils.readingFromSharedPreferences(
                application,
                ConstKeys.USERNAME
            )!!
        )

    fun allFiles(username: String): LiveData<List<File>> =
        fileRepository.getFiles(username)


}