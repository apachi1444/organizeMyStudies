package android.example.organizestudies.data.dao

import android.example.organizestudies.data.entities.File
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFile(file: File)

    @Transaction
    @Query("SELECT * FROM File WHERE  moduleIdCorresponding=:fk")
    fun getFiles(fk: String): List<File>

//    @Query("SELECT * FROM file ORDER BY lastTimeOpened DESC")
//    fun getFilesDependingOnDateOpen()
}