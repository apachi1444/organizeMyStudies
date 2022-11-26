package android.example.organizestudies.ui.main.fragments.moduleDetailsFragment

import android.app.Application
import android.example.organizestudies.data.entities.File
import android.example.organizestudies.data.entities.Module
import android.example.organizestudies.data.repo.FileRepository
import android.example.organizestudies.data.repo.ModuleRepository
import android.example.organizestudies.data.repo.UserModuleCrossRefRepository
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ModuleDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val moduleRepository: ModuleRepository = ModuleRepository(application)
    private val userModuleCrossRefRepository: UserModuleCrossRefRepository =
        UserModuleCrossRefRepository(application)
    private val fileRepository: FileRepository = FileRepository(application)

    fun files(moduleName: String): LiveData<List<File>> {
        return fileRepository.getFilesByModule(moduleName)
    }

    fun getModule(moduleName: String): Module {
        return moduleRepository.getModuleByModuleName(moduleName)
    }

    fun getModuleNameFromNavigation(bundle: Bundle): String {
        val args = ModuleDetailsFragmentArgs.fromBundle(bundle)
        return args.moduleName
    }

    fun starModule(username: String, moduleName: String) {
        userModuleCrossRefRepository.starModule(username, moduleName)
    }

}