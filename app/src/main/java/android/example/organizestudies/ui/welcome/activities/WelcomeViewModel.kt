package android.example.organizestudies.ui.welcome.activities

import android.app.Application
import android.example.organizestudies.data.entities.Module
import android.example.organizestudies.data.entities.enums.HashTagsModules
import android.example.organizestudies.data.repo.ModuleRepository
import android.example.organizestudies.utils.StringsUtils
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WelcomeViewModel(application: Application) : AndroidViewModel(application) {
    private val moduleRepository: ModuleRepository

    init {
        moduleRepository = ModuleRepository(application)
    }


}