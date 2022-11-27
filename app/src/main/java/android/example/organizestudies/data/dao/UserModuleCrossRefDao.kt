package android.example.organizestudies.data.dao

import android.example.organizestudies.data.entities.relations.UserModuleCrossRef
import androidx.lifecycle.LiveData
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
    @Query("UPDATE user_module_table SET starred = CASE starred WHEN 0 THEN 1 ELSE 0 END WHERE username =:username AND moduleName =:moduleName")
    fun starModule(username: String, moduleName: String)

    @Query("SELECT Count(*) FROM user_module_table WHERE username=:username")
    fun countUserModules(username: String): Int

    @Transaction
    @Query("SELECT * FROM user_module_table WHERE username =:username AND starred=1")
    fun getStarredModules(username: String): LiveData<List<UserModuleCrossRef>>

    @Query("UPDATE user_module_table SET starred = CASE starred WHEN 0 THEN 1 ELSE 0 END WHERE moduleName=:moduleName")

    fun toggleStar(moduleName: String)

}