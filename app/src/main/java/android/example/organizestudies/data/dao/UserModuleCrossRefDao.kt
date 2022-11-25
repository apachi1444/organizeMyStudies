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

    @Query("SELECT userModuleIdsCombined FROM user_module_table WHERE username =:username")
    fun getIdCombined(username: String): List<String>

    @Query("SELECT userModuleIdsCombined FROM user_module_table WHERE username =:username AND moduleName =:moduleName LIMIT 1")
    fun getOneIdCombined(username: String, moduleName: String): String


}