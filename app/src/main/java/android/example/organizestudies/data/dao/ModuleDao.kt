package android.example.organizestudies.data.dao

import android.example.organizestudies.data.entities.File
import android.example.organizestudies.data.entities.ModuleWithFiles
import android.example.organizestudies.data.entities.UserWithModules
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

interface ModuleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFile(file: File)

    @Transaction
    @Query("SELECT * FROM Module where moduleId=:moduleId")
    suspend fun getModuleWithFiles(moduleId: String): List<ModuleWithFiles>
}