package android.example.organizestudies.data.repo

import android.app.Application
import android.example.organizestudies.data.dao.UserModuleCrossRefDao
import android.example.organizestudies.data.db.UserDb
import android.example.organizestudies.data.entities.Module
import android.example.organizestudies.data.entities.User

class UserModuleCrossRefRepository(application: Application) {
    private val userModuleCrossRefDao: UserModuleCrossRefDao =
        UserDb.getInstance(application).userModuleCrossRefDao()


    fun getIdCombined(username: String): List<String> {
        return userModuleCrossRefDao.getIdCombined(username)
    }

    fun getOneIdCombined(username: String, moduleName: String): String {
        return userModuleCrossRefDao.getOneIdCombined(username , moduleName)
    }

    fun starModule(username: String, moduleName: String) {
        userModuleCrossRefDao.starModule(username , moduleName)
    }

    fun countUserModules(username: String) : Int {
        return userModuleCrossRefDao.countUserModules(username)
    }

}