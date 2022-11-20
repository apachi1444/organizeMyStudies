package android.example.organizestudies.data.dao

import android.example.organizestudies.data.entities.File
import androidx.room.Dao
import androidx.room.Insert

@Dao
interface FileDao {
    @Insert
    fun insertFile(file : File)
}