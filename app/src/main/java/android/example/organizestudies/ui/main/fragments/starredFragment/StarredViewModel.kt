package android.example.organizestudies.ui.main.fragments.starredFragment

import android.app.Application
import android.example.organizestudies.data.entities.relations.UserWithModules
import android.example.organizestudies.data.repo.ModuleRepository
import android.example.organizestudies.data.repo.UserModuleCrossRefRepository
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class StarredViewModel(application: Application) : AndroidViewModel(application) {
    private val moduleRepository: ModuleRepository = ModuleRepository(application)
    private val userModuleCrossRefRepository: UserModuleCrossRefRepository = UserModuleCrossRefRepository(application)

//    fun modules(username: String): LiveData<List<UserWithModules>> {
//        return userModuleCrossRefRepository.getStarredModules(username)
//    }

}