package android.example.organizestudies.data.dao

import android.example.organizestudies.data.entities.File
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFile(file: File)

    @Transaction
    @Query("SELECT * FROM File WHERE username=:fk")
    fun getFiles(fk: String): LiveData<List<File>>

    @Transaction
    @Query("SELECT * FROM File WHERE moduleName =:moduleName")
    fun getFilesByModule(moduleName: String): LiveData<List<File>>

    @Query("UPDATE File SET starred = CASE starred WHEN 0 THEN 1 ELSE 0 END WHERE filename=:filename")
    fun toggleStar(filename: String)

    @Query("SELECT count(*) FROM File WHERE username =:username")
    fun countUserFiles(username: String): LiveData<Int>

    @Query("SELECT * FROM File WHERE username =:username AND moduleName =:moduleName")
    fun getFilesByModuleAndUsername(moduleName: String, username: String): LiveData<List<File>>

    @Query("DELETE FROM File WHERE filename =:filename")
    fun delete(filename: String)

    @Query("SELECT * FROM File WHERE starred=1")
    fun getStarredFiles(): LiveData<List<File>>

}