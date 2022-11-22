package android.example.organizestudies.data.repo

import android.app.Application
import android.example.organizestudies.data.dao.UserModuleCrossRefDao
import android.example.organizestudies.data.dao.db.UserDb
import android.example.organizestudies.data.entities.Module
import android.example.organizestudies.data.entities.User

class UserModuleCrossRefRepository(private val application: Application) {
    private val userModuleCrossRefDao: UserModuleCrossRefDao =
        UserDb.getInstance(application).userModuleCrossRefDao()

    fun addModulesIntoUser(user: User, modules: List<Module>) {
        modules.forEach {
//            userModuleCrossRefDao.addModuleToUser(user, it)
        }
    }

}