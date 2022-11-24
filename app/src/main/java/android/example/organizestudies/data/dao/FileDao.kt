package android.example.organizestudies.data.dao

import android.example.organizestudies.data.entities.File
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFile(file: File)

//    @Query("SELECT * FROM file ORDER BY lastTimeOpened DESC")
//    fun getFilesDependingOnDateOpen()
}