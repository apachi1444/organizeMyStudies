package android.example.organizestudies.data.repo

import android.app.Application
import android.example.organizestudies.data.dao.FileDao
import android.example.organizestudies.data.db.UserDb
import android.example.organizestudies.data.entities.File
import androidx.lifecycle.LiveData

data class FileRepository(private val application: Application) {

    private var fileDao: FileDao = UserDb.getInstance(application).fileDao()

    fun insertFile(file: File) {
        fileDao.insertFile(file)
    }

    fun getFiles(fk: String): LiveData<List<File>> {
        return fileDao.getFiles(fk)
    }

    fun getFilesByModule(moduleName: String): LiveData<List<File>> {
        return fileDao.getFilesByModule(moduleName)
    }

    fun deleteFileByName(fileName: String) {
        fileDao.delete(fileName)
    }

    fun toggleStar(fileName: String) {
        fileDao.toggleStar(fileName)
    }

    fun countUserFiles(username: String): LiveData<Int> {
        return fileDao.countUserFiles(username)
    }

    fun getFilesByModuleAndUsername(moduleName: String, username: String): LiveData<List<File>> {
        return fileDao.getFilesByModuleAndUsername(moduleName, username)
    }

    fun getStarredFiles(): LiveData<List<File>> {
        return fileDao.getStarredFiles()
    }

}