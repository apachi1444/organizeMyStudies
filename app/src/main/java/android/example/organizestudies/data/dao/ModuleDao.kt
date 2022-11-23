package android.example.organizestudies.data.dao

import android.example.organizestudies.data.entities.File
import android.example.organizestudies.data.entities.Module
import android.example.organizestudies.data.entities.relations.UserModuleCrossRefWithFiles
import androidx.room.*

@Dao
interface ModuleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFile(file: File)

    @Insert
    fun insert(module: Module)

    @Transaction
    @Query("SELECT * FROM Module where moduleId=:moduleId")
    suspend fun getModuleWithFiles(moduleId: String): List<UserModuleCrossRefWithFiles>

    @Query("SELECT * FROM Module WHERE grade=:grade AND levelStudy =:levelStudy")
    fun findModulesByGradeAndLevelStudy(grade: String, levelStudy: String): List<Module>
}