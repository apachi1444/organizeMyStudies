package android.example.organizestudies.data.repo

import android.app.Application
import android.example.organizestudies.data.dao.ModuleDao
import android.example.organizestudies.data.dao.db.UserDb
import android.example.organizestudies.data.entities.Module

data class ModuleRepository(private val application: Application) {

    private var moduleDao: ModuleDao = UserDb.getInstance(application).moduleDao()

    fun add(module: Module) {
        moduleDao.insert(module)
    }

    fun getModulesByGradeAndLevelStudy(grade: String, levelStudy: String): List<Module> {
        return moduleDao.findModulesByGradeAndLevelStudy(grade, levelStudy)
    }

}