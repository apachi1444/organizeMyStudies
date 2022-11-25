package android.example.organizestudies.data.repo

import android.app.Application
import android.example.organizestudies.data.dao.UserModuleCrossRefDao
import android.example.organizestudies.data.db.UserDb
import android.example.organizestudies.data.entities.Module
import android.example.organizestudies.data.entities.User

class UserModuleCrossRefRepository(private val application: Application) {
    private val userModuleCrossRefDao: UserModuleCrossRefDao =
        UserDb.getInstance(application).userModuleCrossRefDao()


    fun getIdCombined(username: String, moduleName: String): String {
        return userModuleCrossRefDao.getIdCombined(username,moduleName)
    }

}