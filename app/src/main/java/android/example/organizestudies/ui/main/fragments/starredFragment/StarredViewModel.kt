package android.example.organizestudies.ui.main.fragments.starredFragment

import android.app.Application
import android.example.organizestudies.data.entities.File
import android.example.organizestudies.data.entities.relations.UserModuleCrossRef
import android.example.organizestudies.data.repo.FileRepository
import android.example.organizestudies.data.repo.UserModuleCrossRefRepository
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class StarredViewModel(application: Application) : AndroidViewModel(application) {
    private val userModuleCrossRefRepository: UserModuleCrossRefRepository =
        UserModuleCrossRefRepository(application)

    private val fileRepository: FileRepository = FileRepository(application)

    fun modules(username: String): LiveData<List<UserModuleCrossRef>> {
        return userModuleCrossRefRepository.getStarredModules(username)
    }

    fun files(): LiveData<List<File>> {
        return fileRepository.getStarredFiles()
    }

}