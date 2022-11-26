package android.example.organizestudies.data.repo

import android.app.Application
import android.example.organizestudies.data.dao.FileDao
import android.example.organizestudies.data.db.UserDb
import android.example.organizestudies.data.entities.File
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class FileRepository(private val application: Application) {

    private var fileDao: FileDao = UserDb.getInstance(application).fileDao()

    fun insertFile(file: File) {
        fileDao.insertFile(file)
    }

    fun getFiles(fk: String): LiveData<List<File>> {
        return fileDao.getFiles(fk)
    }

    fun getFilesByModule(moduleName : String): LiveData<List<File>>{
        return fileDao.getFilesByModule(moduleName)
    }

    fun toggleStar(fileName : String){
        fileDao.toggleStar(fileName)
    }

    fun countUserFiles(username: String): Int {
        return fileDao.countUserFiles(username)
    }

}