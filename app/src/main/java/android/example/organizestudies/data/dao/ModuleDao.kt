package android.example.organizestudies.data.dao

import android.example.organizestudies.data.entities.File
import android.example.organizestudies.data.entities.Module
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ModuleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFile(file: File)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(module: Module)

    @Query("SELECT * FROM Module WHERE grade=:grade AND levelStudy =:levelStudy")
    fun findModulesByGradeAndLevelStudy(grade: String, levelStudy: String): List<Module>

    @Query("SELECT imageModule FROM Module WHERE moduleName =:moduleName")
    fun getModuleImage(moduleName: String): Int
}