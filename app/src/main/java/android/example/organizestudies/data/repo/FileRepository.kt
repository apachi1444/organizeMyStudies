package android.example.organizestudies.data.repo

import android.app.Application
import android.example.organizestudies.data.dao.FileDao
import android.example.organizestudies.data.db.UserDb
import android.example.organizestudies.data.entities.File

data class FileRepository(private val application: Application) {
    private var fileDao: FileDao = UserDb.getInstance(application).fileDao()

    fun insertFile(file: File) {
        fileDao.insertFile(file)
    }
}