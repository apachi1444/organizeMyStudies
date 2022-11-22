package android.example.organizestudies.viewmodels

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

    fun addModules() {
        CoroutineScope(Dispatchers.IO).launch {
            val moduleSpringBoot = Module(
                StringsUtils.generateRandomUUID(),
                "Spring Boot",
                false,
                "Mr Atlas",
                "GI",
                "5th Year",
                1,
                StringsUtils.convertEnumToString(HashTagsModules.BackEnd)
            )
            val moduleBI = Module(
                StringsUtils.generateRandomUUID(),
                "BI",
                false,
                "Mr Ameur",
                "GI",
                "5th Year",
                1,
                StringsUtils.convertEnumToString(HashTagsModules.Data)
            )

            val moduleMobile = Module(
                StringsUtils.generateRandomUUID(),
                "Mobile",
                false,
                "Mme Bouzid",
                "GI",
                "5th Year",
                1,
                StringsUtils.convertEnumToString(HashTagsModules.Data)
            )

            moduleRepository.add(moduleSpringBoot)
            moduleRepository.add(moduleBI)
            moduleRepository.add(moduleMobile)
        }
    }
}