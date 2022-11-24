package android.example.organizestudies.data.dao

import android.example.organizestudies.data.entities.relations.UserModuleCrossRefWithFiles
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface UserModuleCrossRefDao {

    @Transaction
    @Query("SELECT * FROM user_module_table where userModuleIdsCombined=:moduleUserId")
    suspend fun getFiles(moduleUserId: String): List<UserModuleCrossRefWithFiles>

}