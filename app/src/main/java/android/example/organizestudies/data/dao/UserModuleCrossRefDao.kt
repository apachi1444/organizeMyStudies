package android.example.organizestudies.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface UserModuleCrossRefDao {

    @Transaction
    @Query("SELECT userModuleIdsCombined FROM user_module_table WHERE username =:username")
    fun getIdCombined(username: String): List<String>

    @Transaction
    @Query("SELECT userModuleIdsCombined FROM user_module_table WHERE username =:username AND moduleName =:moduleName LIMIT 1")
    fun getOneIdCombined(username: String, moduleName: String): String

    @Transaction
    @Query("UPDATE user_module_table SET starred=1 WHERE username =:username AND moduleName =:moduleName")
    fun starModule(username: String, moduleName: String)

    @Query("SELECT Count(*) FROM user_module_table WHERE username=:username")
    fun countUserModules(username: String): Int

}