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

    fun getFiles(fk: List<String>): LiveData<List<File>> {
        val list: MutableLiveData<List<File>> = MutableLiveData()
        fk.forEach { one_fk ->
            list.value = fileDao.getFiles(one_fk)
        }
        return list
    }

}